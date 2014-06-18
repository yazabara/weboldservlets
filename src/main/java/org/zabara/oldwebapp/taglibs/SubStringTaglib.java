package org.zabara.oldwebapp.taglibs;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Yaroslav_Zabara on 6/17/2014.
 */
public class SubStringTaglib extends TagSupport {

	private String input;
	private int start;
	private int end;
	private static final Logger logger = Logger.getLogger(SubStringTaglib.class.getName());

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.println(input.substring(start, end));
		} catch (IOException e) {
			logger.warn("Custom taglib error", e);
		}
		return SKIP_BODY;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
