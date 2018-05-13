package net.xidlims.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordGenerator {
	private static Configuration configuration = null;
	private static Map<String, Template> allTemplates = null;
	
	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(WordGenerator.class, "/net/xidlims/ftl");
		allTemplates = new HashMap<String, Template>();	// Java 7 钻石语法
		try {
			// 测试普通字段
			allTemplates.put("resume", configuration.getTemplate("resume.ftl"));
			// 测试循环
			allTemplates.put("testFor", configuration.getTemplate("testFor.ftl"));
			// 测试表格
			allTemplates.put("testTable", configuration.getTemplate("testTable.ftl"));
			// 实际业务，综合服务报表
			allTemplates.put("timetableService", configuration.getTemplate("timetableService.ftl"));
			// 实际业务，综合服务报表
			allTemplates.put("labConstruction", configuration.getTemplate("labConstruction.ftl"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private WordGenerator() {
		throw new AssertionError();
	}

	public static File createDoc(Map<?, ?> dataMap, String type, String logoRealPathDir) {
		String name = "temp" + (int) (Math.random() * 100000) + ".doc"; 
	    
	    /** 临时文件放在webapps/property/WEB-INF/classes/temp.txt* */ 
		File f = new File(logoRealPathDir+File.separator+name);
		Template t = allTemplates.get(type);
		try {
			// 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.flush();
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}

}
