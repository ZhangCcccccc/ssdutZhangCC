package net.xidlims.util.mail;

import org.springframework.beans.factory.annotation.Autowired;

import net.xidlims.service.tcoursesite.TMessageService;
public class MailTask {
	@Autowired 
	private TMessageService tMessageService;
	public void autoStatisticWxInfo(){  
        try {  
        	String path=getClass().getResource("/").getFile().toString();//获取类地址
        	String[] s= path.split("/");
        	String curPath="";
        	int flag = 0;
        	for(int i=0;i<s.length;i++)
        	{
        		if(s[i].equals("webapps"))
        		{
        			flag = i;
        		}
        	}
        	for(int j=1;j<flag+2;j++){//拼接出项目绝对地址
        		if(j!=flag+1){
        		curPath +=s[j]+"/";}
        		else{
        			curPath +=s[j];
        		}
        	}
        	tMessageService.sendMassageAtTiming(curPath);
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage(),e);  
        }  
    }  
}
