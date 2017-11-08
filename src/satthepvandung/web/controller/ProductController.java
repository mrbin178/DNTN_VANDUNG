package satthepvandung.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import satthepvandung.dal.dao.SanphamDAO;
import satthepvandung.dal.dao.impl.SanphamDAOImpl;
import satthepvandung.dal.table.Sanpham;
import satthepvandung.model.SanphamForm;
import satthepvandung.web.common.LocaleCustomize;
import satthepvandung.web.common.LocaleType;
import satthepvandung.web.util.Commons;
import satthepvandung.web.util.ConstantValue;
import satthepvandung.web.util.FileManager;
import satthepvandung.web.util.Path;
import satthepvandung.web.util.ReadXMLFileUsingDom;
import satthepvandung.web.util.Utilities;

@Controller
@RequestMapping(value = "/sanpham")
public class ProductController {
	
	private SanphamDAO sanphamDAO;
	
	public ProductController(){
		this.sanphamDAO = new SanphamDAOImpl();
	}
	
	@Autowired
	private MessageSource messageSource;
	
	FileManager fmg = new FileManager();
	Commons coms = new Commons();
	Utilities util = new Utilities();
	ReadXMLFileUsingDom readXML = new ReadXMLFileUsingDom();
	static String folderRoot = Path.PATH_DOWNLOAD;
	
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.setIgnoreInvalidFields(true);
		binder.setIgnoreUnknownFields(true);
	}
	
	@RequestMapping(value = "/list_product.vandung")
	public ModelAndView init(Model model, HttpSession session, HttpServletRequest req) {
		try {
			List<Sanpham> listSp = sanphamDAO.getAll();
			model.addAttribute("listSp", listSp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("danhsach_sanpham");
	}

	@RequestMapping(value = "/insert_product.vandung", method = RequestMethod.POST)
	public ModelAndView insert_product(@ModelAttribute("sanPhamForm") SanphamForm form, Model model, HttpSession session, HttpServletRequest req) {
		String accessKey = "";
		try {
			Sanpham objSave = new Sanpham();
			BeanUtils.copyProperties(form, objSave);
			int save = sanphamDAO.saveProduct(objSave);
			if (save > 0) {
				model.addAttribute("statusSave", 1);
			}
		} catch (Exception e) {
			System.out.println("Caused by");
			e.printStackTrace();
			model.addAttribute(ConstantValue.ACCESSKEY, accessKey);
			model.addAttribute("searchReceiptForm", form);
		}
		return new ModelAndView("search.receipt");
	}

	@RequestMapping(value = "/update_product.vandung", method = RequestMethod.POST)
	public ModelAndView update_product(@ModelAttribute("sanPhamForm") SanphamForm form, Model model, HttpSession session, HttpServletRequest req) {
		try {
			int stt = 0;
			String id = req.getParameter("id");
			Sanpham objSP = sanphamDAO.getById(id);
			if (objSP != null) {
				Sanpham objSave = new Sanpham();
				BeanUtils.copyProperties(form, objSave);
				objSave.setId(objSP.getId());
				int save = sanphamDAO.saveProduct(objSave);
				if (save > 0) {
					stt = 1;	//Update success.
				}
			} else {
				stt = 3;	//Not found 
			}
			model.addAttribute("statusSave", stt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("sanPhamForm", form);
		return new ModelAndView("search.receipt");
	}
	
	private String getMessage(String code){
		return messageSource.getMessage(code, null, LocaleCustomize.getLocale(LocaleType.VI_VN.getLocaleKey()));
	}
	
	private ModelAndView ErrorModelView(String view, String keyMessage) {
		String message;
		ModelAndView mvErr = new ModelAndView(view);
		if(StringUtils.isNotEmpty(keyMessage)){
			message = getMessage(keyMessage);
			mvErr.addObject("errorMessage", message);
		}
		return mvErr;
	}
}