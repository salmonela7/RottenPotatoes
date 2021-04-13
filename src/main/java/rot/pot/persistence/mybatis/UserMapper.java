package rot.pot.persistence.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import rot.pot.entities.mybatis.User;

@Mapper
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.USER
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    int deleteByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.USER
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.USER
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    User selectByPrimaryKey(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.USER
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.USER
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    int updateByPrimaryKey(User record);

    User selectByCredentials(@Param("username") String username, @Param("password") String password);
}