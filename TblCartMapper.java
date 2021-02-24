package jp.co.internous.crocus.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.crocus.model.domain.TblCart;
import jp.co.internous.crocus.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	
	List<CartDto> findByUserId(@Param("userId") int userId);
	
	@Insert("INSERT INTO tbl_Cart (user_id, product_id, product_count)" +
			"VALUES (#{userId}, #{productId}, #{productCount})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(TblCart cart);
	
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = now() WHERE user_id = #{userId} AND product_id = #{productId}")
	int update(TblCart cart);
	
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{userId}")
	int findCountByUserId(@Param("userId") int userId);
	
	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = now() WHERE user_id = #{tmpUserId}")
	int updateUserId(@Param("userId") int userId, @Param("tmpUserId") int tmpUserId);
	
	@Select("SELECT count(id) FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	int findCountByUserIdAndProuductId(@Param("userId") int userId, @Param("productId") int productId);
	
	int deleteById(@Param("checkedIds") List<String> checkedIds);
	
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	int deleteByUserId(@Param("userId") int userId);	

}
