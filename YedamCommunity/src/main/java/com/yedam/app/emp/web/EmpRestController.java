package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController	// @Controller + 모든 메소드에 @ResponseBody를 적용
public class EmpRestController {
	@Autowired
	EmpService empService;
	
	// 전체조회
	@GetMapping("emps")
	public List<EmpVO> empList() {
		return empService.empList();
	}
	
	// 단건조회
	@GetMapping("emps/{id}")
	public EmpVO empInfo(@PathVariable(name = "id") Integer employeeId) {
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(employeeId);
		
		return empService.empInfo(findVO);
	}
	
	// 등록
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	// 수정
	@PutMapping("emps/{id}")
	public Map empUpdate(@PathVariable(name = "id") Integer employeeId, @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		
		return empService.empUpdate(empVO);
	}
	
	// 삭제
	@DeleteMapping("emps/{id}")
	public Map<String, Object> empDelete(@PathVariable Integer id) {
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(id);
		
		return empService.empDelete(findVO);
	}
}
