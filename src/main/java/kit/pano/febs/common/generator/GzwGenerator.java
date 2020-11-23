package kit.pano.febs.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class GzwGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config
                .setActiveRecord(true)
                //作者
                .setAuthor("Pano")
                //生成路径
                .setOutputDir("/Users/pano/Downloads")
                //文件覆盖
                .setFileOverride(true)
                //主键策略
                .setIdType(IdType.AUTO)
                //设置生成的service接口的名字的首字母是否为I(默认Service类前面有一个I)
                .setServiceName("%sService")
                //基本ResultMap
//                .setBaseResultMap(true)
                //基本的列
//                .setBaseColumnList(true)
                //是否直接打开
                .setOpen(true)
                //是否写入swagger配置
                .setSwagger2(false);

        mpg.setGlobalConfig(config);

        // 2. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://172.22.1.124:3307/property_right?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("Guoziwei@mysql2020");

        mpg.setDataSource(dsc);

        // 3. 包配置
        PackageConfig pkConfig = new PackageConfig();

        pkConfig
                //设置父包
                .setParent("cn.gov.xa.gzw")
                .setModuleName(scanner("模块名"))
                //dao层
                .setMapper("mapper")
                //service 层
                .setService("service")
                //controller 层
                .setController("controller")
                //实体类
                .setEntity("entity")
                //接口映射的xml文件
                .setXml("mapper");

        mpg.setPackageInfo(pkConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(scanner("表名，多个英文逗号分割").split(","))
//                .setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
