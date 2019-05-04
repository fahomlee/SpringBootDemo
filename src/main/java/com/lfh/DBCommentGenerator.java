package com.lfh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;
import org.thymeleaf.util.StringUtils;

/**
 * mybatis逆向工程生成注释代码
 * 
 * @author fahomlee
 *
 */
public class DBCommentGenerator extends AbstractCommentGenerator {
	private static final String COMMENT_GENERATOR_AUTHOR = "author";
	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;
	private String author;

	public DBCommentGenerator() {
		super();
		properties = new Properties();
		systemPro = System.getProperties();
		author = properties.getProperty(COMMENT_GENERATOR_AUTHOR);
		suppressDate = false;
		suppressAllComments = false;
		currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		author = properties.getProperty(COMMENT_GENERATOR_AUTHOR);
		suppressDate = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
		suppressAllComments = StringUtility
				.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
	}

	/**
	 * This method adds the custom javadoc tag for. You may do nothing if you do not
	 * wish to include the Javadoc tag - however, if you do not include the Javadoc
	 * tag then the Java merge capability of the eclipse plugin will break.
	 *
	 * @param javaElement the java element
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * This method returns a formated date string to include in the Javadoc tag and
	 * XML comments. You may return null if you do not want the date in these
	 * documentation elements.
	 *
	 * @return a string representing the current timestamp, or null
	 */
	protected String getDateString() {
		String result = null;
		if (!suppressDate) {
			result = currentDateStr;
		}
		return result;
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());
		sb.setLength(0);
		sb.append(" * @author ");
		if (StringUtils.isEmpty(author)) {
			sb.append(systemPro.getProperty("user.name"));
		} else {
			sb.append(author);
		}
		sb.append(" ");
		sb.append(getDateString());
		innerClass.addJavaDocLine(sb.toString());
		innerClass.addJavaDocLine(" */");
	}

	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**");
		// addJavadocTag(innerEnum, false);
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());
		innerEnum.addJavaDocLine(" */");
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString());

		// addJavadocTag(field, false);

		field.addJavaDocLine(" */");
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

	}

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		// method.addJavaDocLine("/**");
		// addJavadocTag(method, false);
		// method.addJavaDocLine(" */");
	}

	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		method.addJavaDocLine("/**");

		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		// addJavadocTag(method, false);

		method.addJavaDocLine(" */");
	}

	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		// addJavadocTag(method, false);

		method.addJavaDocLine(" */");
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());
		sb.setLength(0);
		sb.append(" * @author ");
		if (StringUtils.isEmpty(author)) {
			sb.append(systemPro.getProperty("user.name"));
		} else {
			sb.append(author);
		}
		sb.append(" ");
		sb.append(currentDateStr);

		// addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */");
	}
}
