package test;

import javax.xml.ws.WebServiceClient;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

@WebServiceClient(name = "CronFileConfigService", wsdlLocation = "http://192.168.1.251:668/dmp-service/services/cronConfService?wsdl", targetNamespace = "http://impl.service.dmp.zhiziyun.com/")
public class Test4 {

	public static void main(String[] args) throws NotFoundException {
		Test4 Test4 = new Test4();
		UpdateTest();
	}

	public static void UpdateTest() throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		// 获取需要修改的类
		CtClass ct = pool.get("test.Test4");
		CtField wsdlLocation = ct.getField("Test4");
		System.out.println(wsdlLocation.getFieldInfo().getName());
		// 获取类里的所有方法
		CtMethod[] cms = ct.getDeclaredMethods();
		CtMethod cm = cms[1];
		System.out.println("方法名称====" + cm.getName());

		javassist.bytecode.MethodInfo minInfo = cm.getMethodInfo();
		// 获取类里的em属性
		CtField cf = ct.getField("wsdlLocation");
		javassist.bytecode.FieldInfo fieldInfo = cf.getFieldInfo();

		System.out.println("属性名称===" + cf.getName());

		// ConstPool cp = fieldInfo.getConstPool();
		// // 获取注解信息
		// AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp,
		// AnnotationsAttribute.visibleTag);
		// Annotation annotation = new Annotation(
		// "javax.persistence.PersistenceContext", cp);
		//
		// // 修改名称为unitName的注解
		// annotation.addMemberValue("unitName",
		// new StringMemberValue("basic-entity", cp));
		// attribute2.setAnnotation(annotation);
		// minInfo.addAttribute(attribute2);
		//
		// // 打印修改后方法
		// Annotation annotation2 = attribute2
		// .getAnnotation("javax.persistence.PersistenceContext");
		// String text = ((StringMemberValue) annotation2
		// .getMemberValue("unitName")).getValue();
		//
		// System.out.println("修改后的注解名称===" + text);
	}

}
