package com.star.project.generator;

import com.star.project.generator.simple.SimpleProjectGenerateUtil;
import com.star.project.generator.standard.bean.BeanProjectGenerateUtil;
import com.star.project.generator.standard.common.CommonProjectGenerateUtil;
import com.star.project.generator.standard.core.CoreProjectGenerateUtil;
import com.star.project.generator.standard.parent.ParentProjectGenerateUtil;
import com.star.project.generator.standard.rest.RestProjectGenerateUtil;
import com.star.project.generator.standard.sdk.SdkProjectGenerateUtil;
import com.star.project.generator.utils.FileUtils;

/**
 * 启动类生成
 * 会生成到
 */
public class GenerateStartUp {

    //生成项目地址
    public final static String filePath = "/Users/star/localRepository/git/study/star-open-enterprise-platform/star-demo";
    //项目名称
    public final static String projectName = "star-disruptor";
    //项目说明
    public final static String projectDesc = "状态机demo";

    /**
     * 是否是docker项目，
     * true-->是，
     * false-->否
     */
    public final static Boolean isDockerProject = false;

    /**
     * 项目类型，默认是标准项目
     * standard-->标准项目，
     * simple--->简单项目,
     */
    public final static String projectType = "simple";

    /**
     * 项目配置文件结构:
     * (1) yaml 为纯yml配置，这种结构在启动的时候才确定环境，打出的jar都是一样的
     *（2）yaml_properties，为混合方式，这种结构在打包时候就确定了环境
     */
    public final static String isYaml = "yaml";

    /**
     * 是否是分环境项目，
     * true-->分
     * false-->不分
     */
    public final static Boolean isMultiEnv = false;

    public static void main(String[] args) {

        try {
            //删除项目
            FileUtils.deleteFolders(filePath+"/"+projectName);

            if (projectType.equals("simple")) {
                SimpleProjectGenerateUtil.generateSimpleProject(filePath, projectName, projectDesc, isMultiEnv, isDockerProject);
            } else {
                //生成 parent 项目
                System.out.println("1, ----生成parent项目");
                ParentProjectGenerateUtil.generateParentProject(filePath, projectName, projectDesc);

                //生成 common 项目
                System.out.println("2, ----生成common项目");
                CommonProjectGenerateUtil.generateCommonProject(filePath, projectName, projectDesc);

                System.out.println("3, ----生成Bean项目.");
                BeanProjectGenerateUtil.generateBeanProject(filePath, projectName, projectDesc);

                System.out.println("4, ----生成Core项目.");
                CoreProjectGenerateUtil.generateCoreProject(filePath, projectName, projectDesc);

                System.out.println("6, ----生成Rest项目.");
                RestProjectGenerateUtil.generateRestProject(filePath, projectName, projectDesc, isDockerProject, isYaml);

                System.out.println("6, ----生成Sdk项目.");
                SdkProjectGenerateUtil.generateSdkProject(filePath, projectName, projectDesc);
            }
            System.out.println("项目已将生成，请到 ：请到 ：" + filePath + "路径下查看查看");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}