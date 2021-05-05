package chapter3;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise6 {

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		
		// 자바에서 사용하는 키워드를 넣은 문자열 배열 x
		String[] x = {
			"abstract", 	"assert", 		"boolean", 		"break", 		"byte",
			"case",			"catch",		"char",			"class",		"const",
			"continue",		"default",		"do",			"double",		"else",
			"enum",			"extends",		"final",		"finally",		"float",
			"for",			"goto",			"if",			"implements",	"import",
			"instanceof",	"int",			"interface",	"long",			"native",
			"new",			"package",		"private",		"protected",	"public",
			"return",		"short",		"static",		"strictfp",		"super",
			"switch",		"synchronized",	"this",			"throw",		"throws",
			"transient",	"try",			"void",			"volatile",		"while"
		};
		
		System.out.print("원하는 키워드를 입력하세요 << "); // 키값을 입력하세요
		String ky = data.next();
		data.close();
		
		int idx = Arrays.binarySearch(x, ky);
		
		if(idx<0) System.out.println("해당 키워드가 없습니다.");
		else System.out.println("해당 키워드는 x[" + idx + "]에 있습니다.");
	}

}
