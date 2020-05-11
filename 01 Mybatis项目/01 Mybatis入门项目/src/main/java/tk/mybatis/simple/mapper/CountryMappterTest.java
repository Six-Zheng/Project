package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

public class CountryMappterTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            List<Country> countryList = sqlSession.selectList("selectAll");
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for(Country country:countryList){
            //System.out.println("%-4ld%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
            System.out.println(country.getId() + " " + country.getCountryname() + " " + country.getCountrycode());
        }
    }
}
