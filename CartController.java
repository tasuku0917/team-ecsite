package jp.co.internous.crocus.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.crocus.model.domain.TblCart;
import jp.co.internous.crocus.model.domain.dto.CartDto;
import jp.co.internous.crocus.model.form.CartForm;
import jp.co.internous.crocus.model.mapper.TblCartMapper;
import jp.co.internous.crocus.model.session.LoginSession;

@Controller
@RequestMapping("/crocus/cart")
public class CartController {

	@Autowired 
	private TblCartMapper tblCartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		//sessionからuserId取得
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		//カート情報を取得
		List<CartDto> carts = tblCartMapper.findByUserId(userId);
		// page_header.htmlでsessionの変数を表示させているため、loginSessionも画面に送る。
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("carts", carts);
		return "cart";
	}

	
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		//sessionからuserId取得
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		f.setUserId(userId);
		
		//カート情報の更新/追加
		TblCart cart = new TblCart(f);
		int result = 0;
		//カート情報がある場合
		if(tblCartMapper.findCountByUserIdAndProuductId(userId, f.getProductId()) > 0) {
			result = tblCartMapper.update(cart);
		} 
		//カート情報がない場合
		else {
			result = tblCartMapper.insert(cart);
		}
		//更新・追加後画面表示
		if(result > 0) {
			List<CartDto> carts = tblCartMapper.findByUserId(userId);
			
			// page_header.htmlでsessionの変数を表示させているため、loginSessionも画面に送る。
			m.addAttribute("loginSession", loginSession);
			m.addAttribute("carts", carts);
		}
		return "cart";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		int result = 0;
		
		Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
		List<String> checkedIds = map.get("checkedIdList");
		
		result = tblCartMapper.deleteById(checkedIds);
		return result > 0;
	}
}
