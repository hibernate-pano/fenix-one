package kit.pano.febs.common.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Pano
 * Date: 2019-04-16
 * Time: 14:58e
 */
public class CodeGenerator {

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
            if (StrUtil.isNotEmpty(ipt)) {
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
                //是否支持AR模式
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
                .setSwagger2(true);

        mpg.setGlobalConfig(config);

        // 2. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://47.92.145.171:3306/pano_febs?useUnicode=true&useSSL=false&characterEncoding=utf8")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("root");

        mpg.setDataSource(dsc);

        // 3. 包配置
        PackageConfig pkConfig = new PackageConfig();

        pkConfig
                //设置父包
                .setParent("kit.pano.febs")
                .setModuleName(scanner("模块名"))
                //dao层
                .setMapper("dao")
                //service 层
                .setService("service")
                //controller 层
                .setController("controller")
                //实体类
                .setEntity("domain.po")
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
