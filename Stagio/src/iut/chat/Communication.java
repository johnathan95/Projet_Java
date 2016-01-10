package iut.chat;

import java.io.*;
/**
 * <p>
 * Nom de l'application : STAGIO gestionnaire de stage
 * </p>
 * <p>
 * Description : gestionnaire de stage
 * </p>
 * 
 * @author Johnathan, Joe, Pierre et Thibault
 * @version 1.0
 */
public class Communication implements Serializable {
	private String dest;
	private String msg;
	private String src;

	public Communication(String src, String dest, String msg) {
		this.dest = dest;
		this.src = src;
		this.msg = msg;
	}

	public String get_dest() {
		return dest;
	}

	public String get_src() {
		return src;
	}

	public String get_msg() {
		return msg;
	}

	public String toString() {
		return "[" + src + "-->" + dest + "]" + ": " + msg;
	}

}
