module com.winter.engine {
    //requires lombok;
    exports com.winter.engine;
    exports com.winter.engine.math.parser;
    provides com.winter.engine.math.parser.MathExpression with com.winter.engine.math.parser.MathExpression;
}