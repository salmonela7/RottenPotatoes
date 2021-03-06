package rot.pot.entities.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ACTOR.ACTORID
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    private Integer actorid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ACTOR.DATEOFBIRTH
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    private Date dateofbirth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ACTOR.FIRSTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    private String firstname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.ACTOR.LASTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    private String lastname;

    @Getter
    @Setter
    private List<Movie> movies = new ArrayList<>();

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ACTOR.ACTORID
     *
     * @return the value of PUBLIC.ACTOR.ACTORID
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public Integer getActorid() {
        return actorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ACTOR.ACTORID
     *
     * @param actorid the value for PUBLIC.ACTOR.ACTORID
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public void setActorid(Integer actorid) {
        this.actorid = actorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ACTOR.DATEOFBIRTH
     *
     * @return the value of PUBLIC.ACTOR.DATEOFBIRTH
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public Date getDateofbirth() {
        return dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ACTOR.DATEOFBIRTH
     *
     * @param dateofbirth the value for PUBLIC.ACTOR.DATEOFBIRTH
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ACTOR.FIRSTNAME
     *
     * @return the value of PUBLIC.ACTOR.FIRSTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ACTOR.FIRSTNAME
     *
     * @param firstname the value for PUBLIC.ACTOR.FIRSTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.ACTOR.LASTNAME
     *
     * @return the value of PUBLIC.ACTOR.LASTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.ACTOR.LASTNAME
     *
     * @param lastname the value for PUBLIC.ACTOR.LASTNAME
     *
     * @mbg.generated Mon Apr 12 18:58:58 EEST 2021
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}