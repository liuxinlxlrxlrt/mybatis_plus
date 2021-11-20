package com.mbp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//mp默认将实体类名称当做表名，如果表名和实体类名称不一致，使用注解@TabelName
@TableName("tbl_employee")
public class Employee {
    /**
     * mybatis自动识别实体类中名字为id属性，如果发现有，就会自动当成主键，如果主键没有赋值，
     * 默认会采用"IdType.ID_WORKER"（分布式全局唯一ID 长整型类型)）
     * 主键不能为空，如果发现主键id为空，会使用"IdType.ID_WORKER"策略，
     * 如果字段是自动增长，就需要手动修改生成策略为“@TableId(value = "id",type = IdType.AUTO)”，
     * 防止忘记给主键复制
     */
//    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("last_name")
    private String lastName;
    private String email;
    private String gender;
    private Integer age;

    @TableLogic(value = "1",delval = "0")
    private Integer enabled;

    //创建时间，希望在添加数据时填充为当前时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    //修改时间，添加数据时赋值当前时间，修改时更新时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;

    @Version
    private Integer version;

    //设置实体类中的属性不为数据库的字段
//   @TableField(exist = false)
//    private String genderName;

//    public String getGenderName(){
//        if (gender=="1"){
//            return "男";
//        }else {
//            return "女";
//        }
//    }
}
