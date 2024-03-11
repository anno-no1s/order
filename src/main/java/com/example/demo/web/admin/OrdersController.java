package com.example.demo.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.common.FlashData;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {
	@Autowired
	BaseService<Order> orderService;

	@Autowired
	BaseService<OrderDetail> orderDetailService;

	/*
	 * 一覧表示
	 */
	@GetMapping(path = {"", "/"})
	public String list(Model model) {
		// 全件取得
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "admin/orders/list";
	}

	/*
	 * 新規作成画面表示
	 */
	@GetMapping(value = "/create")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "admin/orders/create";
	}

	/*
	 * 新規登録
	 */
	@PostMapping(value = "/create")
	public String register(@Valid Order order, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "admin/orders/create";
			}
			// 新規登録
			orderService.save(order);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders";
	}

	/*
	 * 表示画面表示
	 */
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		try {
			// 存在確認
			Order order = orderService.findById(id);
			model.addAttribute("order", order);
		} catch (Exception e) {
			FlashData flash = new FlashData().danger("該当データがありません");
			ra.addFlashAttribute("flash", flash);
			return "redirect:/admin/orders";
		}
		return "admin/orders/view";
	}

	/*
	 * 編集画面表示
	 */
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		try {
			// 存在確認
			Order order = orderService.findById(id);
			model.addAttribute("order", order);
		} catch (Exception e) {
			FlashData flash = new FlashData().danger("該当データがありません");
			ra.addFlashAttribute("flash", flash);
			return "redirect:/admin/orders";
		}
		return "admin/orders/edit";
	}

	/*
	 * 更新
	 */
	@PostMapping(value = "/edit/{id}")
	public String update(@PathVariable Integer id, @Valid Order order, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				return "admin/orders/edit";
			}
			orderService.findById(id);
			// 更新
			orderService.save(order);
			flash = new FlashData().success("更新しました");
		} catch (Exception e) {
			flash = new FlashData().danger("該当データがありません");
			ra.addFlashAttribute("flash", flash);
			return "redirect:/admin/orders";
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/{id}";
	}

	/*
	 * 削除
	 */
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes ra) {
		FlashData flash;
		try {
			Order order = orderService.findById(id);
			orderService.deleteById(id);
			for (OrderDetail orderDetail: order.getOrderDetails()) {
				Integer orderDetailId = orderDetail.getId();
				orderDetailService.deleteById(orderDetailId);
			}
			flash = new FlashData().success("削除しました");
		} catch (DataNotFoundException e) {
			flash = new FlashData().danger("該当データがありません");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders";
	}
}
