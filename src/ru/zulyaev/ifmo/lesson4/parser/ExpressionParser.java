package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleBinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoublePositiveArgumentCountFunction;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleSingleArgumentFunction;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleUnaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.Constant;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableFunction;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.Variable;
import ru.zulyaev.ifmo.lesson4.parser.exception.ExpressionMalformed;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class ExpressionParser<E> {

	/**
	 * Used to build {@code ExpressionParser} with defined set of functions
	 */
	final public static class Builder<E> {
		private final List<FunctionWrapper<E>> functions = new ArrayList<>();
		private final List<OperatorWrapper<E>> operators = new ArrayList<>();
		private final Set<Integer> rightAssociativePriorities = new HashSet<>();
		private boolean caseSensitive = false;
		private String variablePattern = "^[a-zA-Z]+";
		private String valuePattern;
		private ValueParser<? extends E> valueParser;

		public Builder() {
		}

		/**
		 * Add function to function set of parser
		 *
		 * @param name     function name
		 * @param function function
		 * @return {@code this}
		 * @throws NullPointerException if any argument is {@code null}
		 */
		public Builder<E> addFunction(String name, Function<E> function) {
			if (name == null || function == null) {
				throw new NullPointerException();
			}

			functions.add(new FunctionWrapper<>(function, name));

			return this;
		}

		/**
		 * Add operator to back of operator list
		 *
		 * @param priority priority of operator
		 * @param token    token representing operator (i.e. "-" for minus)
		 * @param operator operator
		 * @return {@code this}
		 * @throws NullPointerException if any argument is null
		 */
		public Builder<E> addOperator(int priority, String token, Operator<E> operator) {
			if (token == null || operator == null) {
				throw new NullPointerException();
			}

			operators.add(new OperatorWrapper<>(priority, token, operator));

			return this;
		}

		/**
		 * Set priority's right associativity policy
		 * <br/>
		 * i.e.
		 * <br/>
		 * 1+2-3+4-10 == (((((1)+2)-3)+4)-10) - left associative
		 * <br/>
		 * 2^2^3 == (2^(2^(3))) - right associative
		 *
		 * @param priority         priority to assign policy
		 * @param rightAssociative if priority should be right associative
		 * @return {@code this}
		 */
		public Builder<E> setPriorityRightAssociativity(int priority, boolean rightAssociative) {
			if (rightAssociative) {
				rightAssociativePriorities.add(priority);
			} else {
				rightAssociativePriorities.remove(priority);
			}
			return this;
		}

		/**
		 * Set parser case sensitivity
		 *
		 * @param caseSensitive if parser should be case sensitive
		 * @return {@code this}
		 */
		public Builder<E> setCaseSensitive(boolean caseSensitive) {
			this.caseSensitive = caseSensitive;

			return this;
		}

		/**
		 * Set variable name pattern
		 * <br/>
		 * Default value: {@code [a-zA-Z]+}
		 * <br/>
		 * <b>IMPORTANT</b> pattern should not be bounded to string begin or end
		 * <br/>
		 * i.e {@code '^'} and {@code '$'} should not be used
		 *
		 * @param variablePattern variable name pattern
		 * @return {@code this}
		 * @throws NullPointerException if {@code variablePattern == null}
		 */
		public Builder<E> setVariablePattern(String variablePattern) {
			this.variablePattern = "^(" + variablePattern + ")";

			return this;
		}

		/**
		 * Set value pattern
		 * <br/>
		 * Default value: {@code null}
		 * <br/>
		 * <b>IMPORTANT</b> pattern should not be bounded to string begin or end
		 * <br/>
		 * i.e {@code '^'} and {@code '$'} should not be used
		 *
		 * @param valuePattern value pattern
		 * @return {@code this}
		 * @throws NullPointerException if {@code valuePattern == null}
		 */
		public Builder<E> setValuePattern(String valuePattern) {
			this.valuePattern = "^(" + valuePattern + ")";

			return this;
		}


		/**
		 * Set value parser
		 * <br/>
		 * Default value: {@code null}
		 *
		 * @param valueParser value parser
		 * @return {@code this}
		 * @throws NullPointerException if {@code valueParser == null}
		 */
		public Builder<E> setValueParser(ValueParser<? extends E> valueParser) {
			if (valueParser == null) {
				throw new NullPointerException();
			}

			this.valueParser = valueParser;

			return this;
		}

		/**
		 * Build an {@code ExpressionParser} with defined set of functions and operators
		 *
		 * @return parser with function and operator set formed by {@code addFunction} and {@code addOperator}
		 * @throws IllegalStateException if {@code ValueParser} or {@code ValuePattern} is not set
		 */
		public ExpressionParser<E> build() {
			if (valueParser == null) {
				throw new IllegalStateException("ValueParser not set");
			}
			if (valuePattern == null) {
				throw new IllegalStateException("ValuePattern not set");
			}
			return new ExpressionParser<>(
					functions,
					operators,
					rightAssociativePriorities,
					caseSensitive,
					variablePattern,
					valuePattern,
					valueParser);
		}

		/**
		 * Obtain configured double parser builder:
		 * <br/>
		 * <b>Operators:</b>
		 * <ol>
		 * <li>Binary plus and minus ("+", "-")</li>
		 * <li>Binary times and division ("*", "/")</li>
		 * <li>Unary plus and minus  ("+", "-")</li>
		 * <li>Binary right associative power ("^")</li>
		 * </ol>
		 * <b>Functions:</b>
		 * <ul>
		 * <li>sin(x)</li>
		 * <li>cos(x)</li>
		 * <li>tan(x)</li>
		 * <li>sqrt(x)</li>
		 * <li>abs(x)</li>
		 * <li>min(x<sub>0</sub>, x<sub>1</sub>, ...) (1 argument at least)</li>
		 * <li>max(x<sub>0</sub>, x<sub>1</sub>, ...) (1 argument at least)</li>
		 * </ul>
		 *
		 * @return specified builder
		 */
		public static Builder<Double> configuredDoubleParserBuilder() {
			return new Builder<Double>()
					.setCaseSensitive(false)
					.setValueParser(new ValueParser<Double>() {
						@Override
						public Double parse(String expression) {
							return Double.parseDouble(expression);
						}
					})
					.setValuePattern("\\d+\\.?\\d*")

					.addOperator(1, "+", DoubleBinaryOperator.PLUS)
					.addOperator(1, "-", DoubleBinaryOperator.MINUS)
					.addOperator(2, "*", DoubleBinaryOperator.TIMES)
					.addOperator(2, "/", DoubleBinaryOperator.DIVISION)
					.addOperator(3, "+", DoubleUnaryOperator.PLUS)
					.addOperator(3, "-", DoubleUnaryOperator.MINUS)
					.addOperator(4, "^", DoubleBinaryOperator.POWER)

					.setPriorityRightAssociativity(4, true) // Set power right associative


					.addFunction("sin", DoubleSingleArgumentFunction.SIN)
					.addFunction("cos", DoubleSingleArgumentFunction.COS)
					.addFunction("tan", DoubleSingleArgumentFunction.TAN)
					.addFunction("sqrt", DoubleSingleArgumentFunction.SQRT)
					.addFunction("abs", DoubleSingleArgumentFunction.ABS)
					.addFunction("log", DoubleSingleArgumentFunction.LOG)
					.addFunction("min", DoublePositiveArgumentCountFunction.MIN)
					.addFunction("max", DoublePositiveArgumentCountFunction.MAX);
		}
	}

	private static class UnexpectedCharacterException extends Exception {
	}

	private static class OperatorMapKey {
		private final String token;
		private final boolean unary;

		private OperatorMapKey(String token, boolean unary) {
			this.token = token;
			this.unary = unary;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof OperatorMapKey)) return false;

			OperatorMapKey that = (OperatorMapKey) o;

			return unary == that.unary && token.equals(that.token);
		}

		@Override
		public int hashCode() {
			return 31 * token.hashCode() + (unary ? 1 : 0);
		}
	}

	private final Map<String, Function<E>> functions;
	private final List<Map<OperatorMapKey, OperatorWrapper<E>>> operators;
	private final boolean[] rightAssociativePriorities;
	private final boolean caseSensitive;
	private final Pattern variablePattern;
	private final Pattern valuePattern;
	private final ValueParser<? extends E> valueParser;

	private ExpressionParser(
			List<FunctionWrapper<E>> rawFunctionsList,
			List<OperatorWrapper<E>> rawOperatorsList,
			Set<Integer> rightAssociativePrioritiesSet,
			boolean caseSensitive,
			String variablePattern,
			String valuePattern,
			ValueParser<? extends E> valueParser) {

		functions = new HashMap<>();

		for (FunctionWrapper<E> function : rawFunctionsList) {
			String token = function.getToken();
			if (!caseSensitive) {
				token = token.toLowerCase();
			}
			functions.put(token, function.getFunction());
		}

		operators = new ArrayList<>();

		boolean[] oversizedRightAssociativePriorities = new boolean[rawOperatorsList.size()];

		if (!rawOperatorsList.isEmpty()) {
			List<OperatorWrapper<E>> operatorsCopy = new ArrayList<>(rawOperatorsList);
			Collections.sort(operatorsCopy);

			Map<OperatorMapKey, OperatorWrapper<E>> topMap = null;
			int previousPriority = 0;

			for (int i = 0; i < operatorsCopy.size(); ++i) {
				OperatorWrapper<E> operator = operatorsCopy.get(i);
				if (operator.getPriority() != previousPriority || i == 0) {
					operators.add(topMap = new HashMap<>());
					previousPriority = operator.getPriority();

					oversizedRightAssociativePriorities[operators.size() - 1] =
							rightAssociativePrioritiesSet.contains(previousPriority);
				}
				topMap.put(new OperatorMapKey(operator.getToken(caseSensitive), operator.isUnary()), operator);
			}
		}

		rightAssociativePriorities = Arrays.copyOf(oversizedRightAssociativePriorities, operators.size());

		this.caseSensitive = caseSensitive;
		this.valueParser = valueParser;

		int magic = caseSensitive ? 0 : Pattern.CASE_INSENSITIVE;

		this.variablePattern = Pattern.compile(variablePattern, magic);
		this.valuePattern = Pattern.compile(valuePattern, magic);
	}


	private static boolean startsWith(StringBuilder builder, String prefix) {
		int len = prefix.length();
		if (builder.length() < len) {
			return false;
		}
		for (int i = 0; i < len; ++i) {
			if (builder.charAt(i) != prefix.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private static String cutPrefix(StringBuilder builder, int length) {
		String result = builder.substring(0, length);
		builder.delete(0, length);
		return result;
	}

	private static final Pattern spacePattern = Pattern.compile("^\\s+");

	private Token nextToken(StringBuilder expression) throws UnexpectedCharacterException {
		while (true) {
			if (expression.length() == 0) {
				return Token.END;
			}

			Matcher spaces = spacePattern.matcher(expression);
			if (!spaces.find()) {
				break;
			}

			expression.delete(0, spaces.end());
		}

		Matcher valueMatcher = valuePattern.matcher(expression);
		if (valueMatcher.find()) {
			return Token.newValue(cutPrefix(expression, valueMatcher.end()));
		}

		for (Map<OperatorMapKey, OperatorWrapper<E>> map : operators) {
			for (OperatorMapKey key : map.keySet()) {
				String token = key.token;
				if (startsWith(expression, token)) {
					expression.delete(0, token.length());
					return Token.newOperator(token);
				}
			}
		}

		for (Map.Entry<String, Function<E>> func : functions.entrySet()) {
			String token = func.getKey();
			if (startsWith(expression, token)) {
				expression.delete(0, token.length());
				return Token.newFunction(token);
			}
		}

		{
			Matcher variableMatcher = variablePattern.matcher(expression);
			if (variableMatcher.find()) {
				return Token.newVariable(cutPrefix(expression, variableMatcher.end()));
			}
		}

		char firstChar = expression.charAt(0);
		if ("(),".indexOf(firstChar) != -1) {
			expression.delete(0, 1);
			switch (firstChar) {
				case '(':
					return Token.OPEN;
				case ')':
					return Token.CLOSE;
				case ',':
					return Token.DELIMITER;
			}
		}

		throw new UnexpectedCharacterException();
	}

	private Queue<Token> tokenize(String expression) throws ExpressionMalformed {
		Queue<Token> tokens = new LinkedList<>();
		StringBuilder builder = new StringBuilder(expression);

		while (builder.length() != 0) {
			try {
				tokens.offer(nextToken(builder));
			} catch (UnexpectedCharacterException ex) {
				int position = expression.length() - builder.length();
				throw new ExpressionMalformed("Unexpected character '" + expression.charAt(position) + "' at " + position);
			}
		}

		tokens.add(Token.END);

		return tokens;
	}

	private Evaluable<E> parseExpr(Queue<Token> tokens, int priority) throws ExpressionMalformed {
		if (priority == operators.size()) {
			return parseItem(tokens);
		}

		boolean rightAssociative = rightAssociativePriorities[priority];

		Token token = tokens.peek();

		Map<OperatorMapKey, OperatorWrapper<E>> operators = this.operators.get(priority);

		Evaluable<E> result = null;

		if (token.type == TokenType.OPERATOR) {
			OperatorWrapper<E> operator = operators.get(new OperatorMapKey(token.token, true));
			if (operator != null) {
				tokens.remove();

				if (rightAssociative) {
					return operator.asEvaluable(parseExpr(tokens, priority), rightAssociative);
				}

				result = operator.asEvaluable(parseExpr(tokens, priority + 1), rightAssociative);
			}
		}

		if (result == null) {
			result = parseExpr(tokens, priority + 1);
		}

		while (true) {
			token = tokens.peek();

			if (token.type != TokenType.OPERATOR) {
				break;
			}

			OperatorWrapper<E> operator = operators.get(new OperatorMapKey(token.token, false));

			if (operator == null) {
				break;
			}

			tokens.remove();

			if (rightAssociative) {
				return operator.asEvaluable(result, parseExpr(tokens, priority), rightAssociative);
			}

			result = operator.asEvaluable(result, parseExpr(tokens, priority + 1), rightAssociative);
		}

		return result;
	}

	private Evaluable<E> parseExpr(Queue<Token> tokens) throws ExpressionMalformed {
		return parseExpr(tokens, 0);
	}

	private Evaluable<E> parseItem(Queue<Token> tokens) throws ExpressionMalformed {
		Token token = tokens.peek();
		Evaluable<E> result;
		switch (token.type) {
			case OPEN:
				tokens.remove();
				result = parseExpr(tokens);
				if (tokens.peek() != Token.CLOSE) {
					throw new ExpressionMalformed("Closing token expected but '" + tokens.peek() + "' found");
				}
				tokens.remove();
				break;
			case VALUE:
				result = new Constant<>(valueParser.parse(token.token));
				tokens.remove();
				break;
			case VARIABLE:
				result = new Variable<>(token.token, caseSensitive);
				tokens.remove();
				break;
			case FUNCTION:
				Function<E> function = functions.get(token.token);
				tokens.remove();
				if (tokens.peek() != Token.OPEN) {
					throw new ExpressionMalformed("Open token after function expected but '" + tokens.peek() + "' found");
				}
				tokens.remove();

				List<Evaluable<E>> arguments = new ArrayList<>();

				if (tokens.peek() == Token.CLOSE) {
					tokens.remove();
				} else {
					arguments.add(parseExpr(tokens));

					while (tokens.peek() == Token.DELIMITER) {
						tokens.remove();
						arguments.add(parseExpr(tokens));
					}

					if (tokens.peek() != Token.CLOSE) {
						throw new ExpressionMalformed("Close or delimiter token expected but '" + tokens.peek() + "' found");
					}

					tokens.remove();
				}

				try {
					result = EvaluableFunction.withArgumentsAndToken(function, arguments, token.token);
				} catch (IllegalArgumentException ex) {
					throw new ExpressionMalformed(ex.getMessage(), ex);
				}
				break;
			default:
				throw new ExpressionMalformed("Unexpected token " + token);
		}

		return result;
	}

	/**
	 * Parse specified expression and return value that expression represents
	 *
	 * @param expression expression to parse
	 * @return {@code Evaluable} representing parsed expression
	 * @throws ExpressionMalformed if expression is malformed
	 */
	public Evaluable<E> parse(String expression) throws ExpressionMalformed {
		if (expression == null) {
			throw new NullPointerException();
		}
		try {
			if (!caseSensitive) {
				expression = expression.toLowerCase();
			}
			Queue<Token> tokens = tokenize(expression);
			Evaluable<E> result = parseExpr(tokens);
			Token tokenLeft = tokens.peek();
			if (tokenLeft.type != TokenType.END) {
				throw new ExpressionMalformed("Unexpected token " + tokenLeft);
			}
			return result;
		} catch (ExpressionMalformed ex) {
			throw ex;
		} catch (Throwable ex) {
			throw new ExpressionMalformed("Unknown error", ex);
		}
	}
}