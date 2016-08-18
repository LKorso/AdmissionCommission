package com.admissioncommission.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomSelectTag extends SimpleTagSupport{
	private List<String> values = new ArrayList<>();
	private String selectClass;
	private String criterionForSelected;
	private String selectName;
	private String selectId;
	
	public void setValues(List<String> values) {
		this.values.addAll(values);
	}
	
	public void setSelectClass(String selectClass) {
		this.selectClass = selectClass;
	}

	public void setCriterionForSelected(String criterionForSelected) {
		this.criterionForSelected = criterionForSelected;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}
	
	public void setSelectId(String selectId){
		this.selectId = selectId;
	}
	
	@Override
	public void doTag() throws JspException {
		PageContext pageContext = (PageContext) getJspContext();
		try {
			pageContext.getOut().write(getSelectTag());
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		
	}
	
	private String getSelectTag(){
		StringBuilder tagBuilder = new StringBuilder();
		
		tagBuilder.append("<select class=\"");
		tagBuilder.append(selectClass);
		tagBuilder.append("\" name=\"");
		tagBuilder.append(selectName);
		tagBuilder.append("\" id=\"");
		tagBuilder.append(selectId);
		tagBuilder.append("\">");
		
		for(String value : values){
			if(value.equals(criterionForSelected)){
				tagBuilder.append(" <option selected=\"selected\" value=\"");
				tagBuilder.append(value);
				tagBuilder.append("\">");
				tagBuilder.append(value);
				tagBuilder.append("</option>");
			} else {
				tagBuilder.append(" <option value=\"");
				tagBuilder.append(value);
				tagBuilder.append("\">");
				tagBuilder.append(value);
				tagBuilder.append("</option>");
			}
		}
		
		tagBuilder.append("</select>");
		
		return tagBuilder.toString();
	}
}
