package com.tuling;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GeneratorApp {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        //判断用户是否输入
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        String modelName = scanner("请输入模块名");
        String tableName = scanner("请输入表名（多个用逗号隔开，或者按前缀（pms*））");
        String prefixName = scanner("请输入需要替换的前缀");
        //代码生成器
        AutoGenerator generator = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //获取当前项目的路径
        String projectPath = System.getProperty("user.dir") + "/05_generator";
        System.out.println(projectPath);
        //当前项目路径为：D:\javaCode\21_ProjectStorageFolder\automatic\java\mybatis_plus/

        //把当前代码路径设置到生成路径
        globalConfig.setOutputDir(projectPath + "/src/main/java");

        //作者
        globalConfig.setAuthor("Liu Xin");

        //代码生成后是否要打开所在文件夹
        globalConfig.setOpen(false);

        //生成Swagger2注解
        globalConfig.setSwagger2(true);

        //会在mapper.xml中生成<BaseResultMap>映射所有的表字段
        globalConfig.setBaseResultMap(true);

        //设置同文件生成后是否覆盖
        globalConfig.setFileOverride(true);
        //设置生成时间格式（可选）
        //        globalConfig.setDateType(DateType.ONLY_DATE);

        //设置生成实体名称，%s=表名
        globalConfig.setEntityName("%s");

        //mapper接口名，表名+Mapper
        globalConfig.setMapperName("%sMapper");
        //Mapper.xml的文件名
        globalConfig.setXmlName("%sMapper");
        //业务逻辑类的接口名称service
        globalConfig.setServiceName("%sService");
        //业务逻辑类实现类名称
        globalConfig.setServiceImplName("%sServiceImpl");
        //将全局配置设置到AutoGenerator中
        generator.setGlobalConfig(globalConfig);


        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncodeing=utf8&serverTimezone=UTC&useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("lx@lx19870613");
        generator.setDataSource(dataSourceConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        //模块名称
//        packageConfig.setModuleName("edu");
        packageConfig.setModuleName(modelName);
        //包名
        packageConfig.setParent("com.tuling");
        //完整的包名就是：com.tuling.edu，包里面就有Entity、mapper、service、controller
        generator.setPackageInfo(packageConfig);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表名的生成策略：下划线转驼峰命名，edu_course_description -->EduCourseDescription
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //表的列名生成策略：下划线转驼峰命名，last_name -->lastName
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //如果实体类中有父类，有的话就设置，没有就不用设置，比如id,createDate,modifyDate
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //实体类是否支持lombok
        strategy.setEntityLombokModel(true);
        //设置controller是不是用RestController
        strategy.setRestControllerStyle(true);
        // 公共父类（controller是不是有父类，没有就注释掉不设置）
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");

        //要生成的表名，多个用逗号分开
        //如果数据库中没有这个表，不会报错，只是不会生成类
//        strategy.setInclude("edu_course");
        if (tableName.indexOf("*") > 0) {

            //按前缀生成表(通过前缀一次性生成多张表，就设置这个)
            strategy.setLikeTable(new LikeTable(tableName.replace("*", "_")));
        } else {
            //要生成的表名，多个用逗号分开
            //如果数据库中没有这个表，不会报错，只是不会生成类
            strategy.setInclude(tableName);
        }

        //按前缀生成表(通过前缀一次性生成多张表，就设置这个)
//        strategy.setLikeTable(new LikeTable("edu_"));
        //驼峰转连接符设置成功true，比如表是edu_course-->controller@RequestMapping("/edu/edu_course")
        //驼峰转连接符设置成功false或者没有设置，比如表是edu_course-->controller@RequestMapping("/edu/eduCourse")
//        strategy.setControllerMappingHyphenStyle(true);

        //设置表的替换前缀过滤掉edu_,生成的类就不会加上"edu_"
//      strategy.setTablePrefix("edu_");
        strategy.setTablePrefix(prefixName);
        generator.setStrategy(strategy);
        //执行生成
        generator.execute();
    }
}
