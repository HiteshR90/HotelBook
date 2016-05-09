package com.hr.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.hr.hotel.dto.TempSellRoomDto;
import com.hr.hotel.model.User;

import freemarker.template.TemplateException;

public interface AdminService extends UserService {

	public List<TempSellRoomDto> getRooms();

	public void confirmRoom(Long id, User loginUser) throws MessagingException,
			IOException, TemplateException;

	public void rejectRoom(Long id, User loginUser) throws MessagingException,
			IOException, TemplateException;

}
