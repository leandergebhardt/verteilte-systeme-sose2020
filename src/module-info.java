module edu.sb.ds {
	requires transitive java.compiler;
	requires transitive java.logging;
	requires transitive java.management;
	requires transitive java.sql;
	requires transitive java.rmi;
	requires transitive jdk.httpserver;

	exports edu.sb.ds.util;
}