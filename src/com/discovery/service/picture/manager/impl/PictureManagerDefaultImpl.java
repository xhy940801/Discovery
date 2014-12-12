package com.discovery.service.picture.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.message.Message;
import com.discovery.service.message.impl.ErrorMessage;
import com.discovery.service.message.impl.GeneralMessage;
import com.discovery.service.message.impl.PictureMessage;
import com.discovery.service.picture.dao.PictureInfoDAO;
import com.discovery.service.picture.manager.PictureManager;
import com.discovery.service.picture.model.PictureInfo;
import com.discovery.service.push.dao.PushRecordDAO;
import com.discovery.service.push.model.PushRecord;

public class PictureManagerDefaultImpl implements PictureManager {

	private static Date standardDate;
	private PictureInfoDAO pictureInfoDAO;
	private PushRecordDAO pushRecordDAO;
	
	static{
		standardDate = new Date();
		standardDate.setYear(2014);
		standardDate.setMonth(9);
		standardDate.setDate(1);
		standardDate.setHours(0);
		standardDate.setMinutes(0);
		standardDate.setSeconds(0);
	}
	
	public void setPictureInfoDAO(PictureInfoDAO pictureInfoDAO) {
		this.pictureInfoDAO = pictureInfoDAO;
	}

	public void setPushRecordDAO(PushRecordDAO pushRecordDAO) {
		this.pushRecordDAO = pushRecordDAO;
	}

	@Override
	public Message addPicture(int fileId, int userId, float longitude,
			float latitude, String remark) {
		// TODO Auto-generated method stub
		try{
			PictureInfo pictureInfo = new PictureInfo();
			pictureInfo.setFileId(fileId);
			pictureInfo.setLongitude(longitude);
			pictureInfo.setLatitude(latitude);
			pictureInfo.setUserSecuInfoId(userId);
			pictureInfo.setTotalLikeNum(0);
			pictureInfo.setTotalTemperature(0);
			pictureInfo.setCreatedTime(new Date());
			pictureInfo.setModifyLikeTime(new Date());
			pictureInfo.setModifyTempTime(new Date());
			pictureInfo.setRemark(remark);
			pictureInfo.setLikeNum0(0);
			pictureInfo.setLikeNum1(0);
			pictureInfo.setLikeNum2(0);
			pictureInfo.setLikeNum3(0);
			pictureInfo.setLikeNum4(0);
			pictureInfo.setLikeNum5(0);
			pictureInfo.setLikeNum6(0);
			pictureInfo.setLikeNum7(0);
			pictureInfo.setLikeNum8(0);
			pictureInfo.setLikeNum9(0);
			pictureInfo.setTemperature0(0);
			pictureInfo.setTemperature1(0);
			pictureInfo.setTemperature2(0);
			pictureInfo.setTemperature3(0);
			pictureInfo.setTemperature4(0);
			pictureInfo.setTemperature5(0);
			pictureInfo.setTemperature6(0);
			pictureInfo.setTemperature7(0);
			pictureInfo.setTemperature8(0);
			pictureInfo.setTemperature9(0);
			
			pictureInfoDAO.save(pictureInfo);
			
		}catch(HibernateException e){
			return new ErrorMessage(403010, null);
		}catch(Exception e){
			return new ErrorMessage(503010, null);
		}catch(Throwable e){
			return new ErrorMessage(703010, null);
		}
		return new GeneralMessage(0, pictureInfo.getId());
	}

	@Override
	public Message deletePicture(int pictureId) {
		// TODO Auto-generated method stub
		try {
			pictureInfoDAO.delete(pictureId);
		}catch(HibernateException e){
			return new ErrorMessage(403020, null);
		}catch(Exception e){
			return new ErrorMessage(503020, null);
		}catch(Throwable e){
			return new ErrorMessage(703020, null);
		}
		return new GeneralMessage(0, null);
	}

	@Override
	public Message like(int pictureId, int count) {
		// TODO Auto-generated method stub
		try {
			
			PictureInfo pictureInfo = pictureInfoDAO.getById(pictureId);
			
			PushRecord pushRecord = pushRecordDAO.getByPictureId(pictureId);
			
			likeUpdate(pictureInfo, pushRecord, count);
			
		}catch(HibernateException e){
			return new ErrorMessage(403030, null);
		}catch(Exception e){
			return new ErrorMessage(503030, null);
		}catch(Throwable e){
			return new ErrorMessage(703030, null);
		}
		return new GeneralMessage(0, null);
	}

	@Override
	public Message addTemperature(int pictureId, int count) {
		// TODO Auto-generated method stub
		try {
			
			PictureInfo pictureInfo = pictureInfoDAO.getById(pictureId);
			
			temperatureUpdate(pictureInfo, count);
			
		}catch(HibernateException e){
			return new ErrorMessage(403040, null);
		}catch(Exception e){
			return new ErrorMessage(503040, null);
		}catch(Throwable e){
			return new ErrorMessage(703040, null);
		}
		return new GeneralMessage(0, null);
	}
	
	@Override
	public Message getPicture(int pictureId) {
		// TODO Auto-generated method stub
		List<PictureInfo> pictureInfos = new LinkedList<PictureInfo>();
		try {
			
			pictureInfos.add(pictureInfoDAO.getById(pictureId));
			
		}catch(HibernateException e){
			return new ErrorMessage(403050, null);
		}catch(Exception e){
			return new ErrorMessage(503050, null);
		}catch(Throwable e){
			return new ErrorMessage(703050, null);
		}
		return new PictureMessage(0,pictureInfos);
	}

	@Override
	public Message getPictureList(List<Integer> list) {
		// TODO Auto-generated method stub
		List<PictureInfo> pictureInfos = new LinkedList<PictureInfo>();
		try {
			
			for(Integer i:list){
				pictureInfos.add(pictureInfoDAO.getById(i));
			}
			
		}catch(HibernateException e){
			return new ErrorMessage(403060, null);
		}catch(Exception e){
			return new ErrorMessage(503060, null);
		}catch(Throwable e){
			return new ErrorMessage(703060, null);
		}
		return new PictureMessage(0,pictureInfos);
	}
	
	@Transactional
	private void likeUpdate(PictureInfo pictureInfo,PushRecord pushRecord,int count){
		
		pictureInfo.setTotalLikeNum(pictureInfo.getTotalLikeNum()+count);
		
		Date nowDate = new Date();
		nowDate.setHours(0);
		nowDate.setMinutes(0);
		nowDate.setSeconds(0);
		
		Date lastDate = pictureInfo.getModifyLikeTime();
		lastDate.setHours(0);
		lastDate.setMinutes(0);
		lastDate.setSeconds(0);
		
		int lastDay = ((int)(lastDate.getTime() - standardDate.getTime()) / 86400000) % 10;
		int dayBox = lastDay;
		
		long spaceTime = nowDate.getTime() - lastDate.getTime();
		int spaceDay = (int)spaceTime / 86400000;
		
		if(spaceDay == 0){
			
			switchLikeAdd(pictureInfo, dayBox, count);
			
		}else if(spaceDay >= 10){
			
			resetAllLike(pictureInfo);
			
			while(spaceDay >= 10){
				spaceDay -= 10;
			}
			
			dayBox += spaceDay;
			if(dayBox >= 10){
				dayBox -= 10;
			}
			switchLikeSet(pictureInfo, dayBox, count);
			
		}else{
		
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0; i < spaceDay; ++i){
				dayBox += 1;
				list.add(dayBox);
				if(dayBox >= 10){
					dayBox -= 10;
				}
			}
			
			for(Integer integer : list){
				switchLikeSet(pictureInfo, integer, 0);
			}
			
			switchLikeSet(pictureInfo, dayBox, count);
		}
		pictureInfo.setModifyLikeTime(new Date());
		pictureInfoDAO.update(pictureInfo);
		
		pushRecord.setAllCount(pushRecord.getAllCount()+5);
		pushRecord.setRemainCount(pushRecord.getRemainCount()+5);
		pushRecordDAO.update(pushRecord);
	}
	
	private void switchLikeSet(PictureInfo pictureInfo,int number,int count){
		switch(number){
			case 0:{
				pictureInfo.setLikeNum0(count);
				break;
			}
			case 1:{
				pictureInfo.setLikeNum1(count);
				break;
			}
			case 2:{
				pictureInfo.setLikeNum2(count);
				break;
			}
			case 3:{
				pictureInfo.setLikeNum3(count);
				break;
			}
			case 4:{
				pictureInfo.setLikeNum4(count);
				break;
			}
			case 5:{
				pictureInfo.setLikeNum5(count);
				break;
			}
			case 6:{
				pictureInfo.setLikeNum6(count);
				break;
			}
			case 7:{
				pictureInfo.setLikeNum7(count);
				break;
			}
			case 8:{
				pictureInfo.setLikeNum8(count);
				break;
			}
			case 9:{
				pictureInfo.setLikeNum9(count);
				break;
			}
		}
	}
	
	private void resetAllLike(PictureInfo pictureInfo){
		pictureInfo.setLikeNum0(0);
		pictureInfo.setLikeNum1(0);
		pictureInfo.setLikeNum2(0);
		pictureInfo.setLikeNum3(0);
		pictureInfo.setLikeNum4(0);
		pictureInfo.setLikeNum5(0);
		pictureInfo.setLikeNum6(0);
		pictureInfo.setLikeNum7(0);
		pictureInfo.setLikeNum8(0);
		pictureInfo.setLikeNum9(0);
	}
	
	private void switchLikeAdd(PictureInfo pictureInfo,int number,int count){
		switch(number){
			case 0:{
				pictureInfo.setLikeNum0(pictureInfo.getLikeNum0() + count);
				break;
			}
			case 1:{
				pictureInfo.setLikeNum1(pictureInfo.getLikeNum1() + count);
				break;
			}
			case 2:{
				pictureInfo.setLikeNum2(pictureInfo.getLikeNum2() + count);
				break;
			}
			case 3:{
				pictureInfo.setLikeNum3(pictureInfo.getLikeNum3() + count);
				break;
			}
			case 4:{
				pictureInfo.setLikeNum4(pictureInfo.getLikeNum4() + count);
				break;
			}
			case 5:{
				pictureInfo.setLikeNum5(pictureInfo.getLikeNum5() + count);
				break;
			}
			case 6:{
				pictureInfo.setLikeNum6(pictureInfo.getLikeNum6() + count);
				break;
			}
			case 7:{
				pictureInfo.setLikeNum7(pictureInfo.getLikeNum7() + count);
				break;
			}
			case 8:{
				pictureInfo.setLikeNum8(pictureInfo.getLikeNum8() + count);
				break;
			}
			case 9:{
				pictureInfo.setLikeNum9(pictureInfo.getLikeNum9() + count);
				break;
			}
		}
	}

	@Transactional
	private void temperatureUpdate(PictureInfo pictureInfo,int count){
		pictureInfo.setTotalTemperature(pictureInfo.getTotalTemperature()+count);
		
		Date nowDate = new Date();
		nowDate.setHours(0);
		nowDate.setMinutes(0);
		nowDate.setSeconds(0);
		
		Date lastDate = pictureInfo.getModifyTempTime();
		lastDate.setHours(0);
		lastDate.setMinutes(0);
		lastDate.setSeconds(0);
		
		int lastDay = ((int)(lastDate.getTime() - standardDate.getTime()) / 86400000) % 10;
		int dayBox = lastDay;
		
		long spaceTime = nowDate.getTime() - lastDate.getTime();
		int spaceDay = (int)spaceTime / 86400000;
		
		if(spaceDay == 0){
			
			switchTempAdd(pictureInfo, dayBox, count);
			
		}else if(spaceDay >= 10){
			
			resetAllTemp(pictureInfo);
			
			while(spaceDay >= 10){
				spaceDay -= 10;
			}
			
			dayBox += spaceDay;
			if(dayBox >= 10){
				dayBox -= 10;
			}
			switchTempSet(pictureInfo, dayBox, count);
			
		}else{
		
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0; i < spaceDay; ++i){
				dayBox += 1;
				list.add(dayBox);
				if(dayBox >= 10){
					dayBox -= 10;
				}
			}
			
			for(Integer integer : list){
				switchTempSet(pictureInfo, integer, 0);
			}
			
			switchTempSet(pictureInfo, dayBox, count);
		}
		pictureInfo.setModifyTempTime(new Date());
		pictureInfoDAO.update(pictureInfo);
	}
	
	private void switchTempSet(PictureInfo pictureInfo,int number,int count){
		switch(number){
		case 0:{
			pictureInfo.setTemperature0(count);
			break;
		}
		case 1:{
			pictureInfo.setTemperature1(count);
			break;
		}
		case 2:{
			pictureInfo.setTemperature2(count);
			break;
		}
		case 3:{
			pictureInfo.setTemperature3(count);
			break;
		}
		case 4:{
			pictureInfo.setTemperature4(count);
			break;
		}
		case 5:{
			pictureInfo.setTemperature5(count);
			break;
		}
		case 6:{
			pictureInfo.setTemperature6(count);
			break;
		}
		case 7:{
			pictureInfo.setTemperature7(count);
			break;
		}
		case 8:{
			pictureInfo.setTemperature8(count);
			break;
		}
		case 9:{
			pictureInfo.setTemperature9(count);
			break;
		}
		}
	}
	
	private void resetAllTemp(PictureInfo pictureInfo){
		pictureInfo.setTemperature0(0);
		pictureInfo.setTemperature1(0);
		pictureInfo.setTemperature2(0);
		pictureInfo.setTemperature3(0);
		pictureInfo.setTemperature4(0);
		pictureInfo.setTemperature5(0);
		pictureInfo.setTemperature6(0);
		pictureInfo.setTemperature7(0);
		pictureInfo.setTemperature8(0);
		pictureInfo.setTemperature9(0);
	}
	
	private void switchTempAdd(PictureInfo pictureInfo,int number,int count){
		switch(number){
		case 0:{
			pictureInfo.setTemperature0(pictureInfo.getTemperature0() + count);
			break;
		}
		case 1:{
			pictureInfo.setTemperature1(pictureInfo.getTemperature1() + count);
			break;
		}
		case 2:{
			pictureInfo.setTemperature2(pictureInfo.getTemperature2() + count);
			break;
		}
		case 3:{
			pictureInfo.setTemperature3(pictureInfo.getTemperature3() + count);
			break;
		}
		case 4:{
			pictureInfo.setTemperature4(pictureInfo.getTemperature4() + count);
			break;
		}
		case 5:{
			pictureInfo.setTemperature5(pictureInfo.getTemperature5() + count);
			break;
		}
		case 6:{
			pictureInfo.setTemperature6(pictureInfo.getTemperature6() + count);
			break;
		}
		case 7:{
			pictureInfo.setTemperature7(pictureInfo.getTemperature7() + count);
			break;
		}
		case 8:{
			pictureInfo.setTemperature8(pictureInfo.getTemperature8() + count);
			break;
		}
		case 9:{
			pictureInfo.setTemperature9(pictureInfo.getTemperature9() + count);
			break;
		}
		}
	}
}
