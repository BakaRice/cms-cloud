package com.ricemarch.cms.pms;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * mybatis plus code generator
 *
 * @author RiceMarch
 * @date 2021/3/5 19:46
 */
public class CodeGenerator {

    @Test
    public void run() {

        //创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //Agriculturalproductms
        //D:\tanwentao\project\cms\src\test\java\com\ricemarch\cms
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir("E:\\Graduation\\cms-cloud\\cms-pms\\src\\test\\java\\com\\ricemarch\\cms\\pms");
        gc.setAuthor("ricemarch");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
//        gc.setIdType(IdType.AUTO); //主键策略
//        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
//        gc.setSwagger2(true);//开启Swagger2模式
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://test.ricemarch.com:3306/cms_pms? serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false");
        dsc.setUrl("jdbc:postgresql://test.ricemarch.com:5432/cms_pms_pg");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("postgres");
        dsc.setPassword("NEFU");
//        dsc.setDbType(DateType.ONLY_DATE);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("pms"); //模块名
        pc.setParent("com.ricemarch.cms");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("scheduling");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
