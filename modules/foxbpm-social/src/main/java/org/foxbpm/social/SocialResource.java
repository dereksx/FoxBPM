///**
// * Copyright 1996-2014 FoxBPM ORG.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *      http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * 
// * @author MAENLIANG
// */
//package org.foxbpm.social;
//
//import java.net.URLDecoder;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.TimeZone;
//import java.util.UUID;
//
//import org.foxbpm.engine.ProcessEngineManagement;
//import org.foxbpm.engine.impl.util.StringUtil;
//import org.foxbpm.rest.common.api.AbstractRestResource;
//import org.foxbpm.social.SocialService;
//import org.foxbpm.social.impl.entity.SocialMessageInfo;
//import org.restlet.data.Form;
//import org.restlet.representation.Representation;
//import org.restlet.resource.Get;
//import org.restlet.resource.Post;
//
///**
// * 社交rest资源类
// * 
// * @author Administrator
// * 
// */
//public class SocialResource extends AbstractRestResource {
//	@Get
//	public Object findSocialMessageInfo() {
//		Form queryForm = getQuery();
//		String msgType = getQueryParameter("msgType", queryForm);
//		SocialService socialService = ProcessEngineManagement
//				.getDefaultProcessEngine().getService(SocialService.class);
//		List<SocialMessageInfo> allSocialMessageInfo = null;
//		if (StringUtil.equals(msgType, "findAll")) {
//			// 获取参数
//			String taskId = getQueryParameter("taskId", queryForm);
//			if (StringUtil.isNotEmpty(taskId)) {
//				allSocialMessageInfo = socialService
//						.findAllSocialMessageInfo(taskId);
//				if (null != allSocialMessageInfo) {
//					return allSocialMessageInfo;
//				}
//			}
//		} else if (StringUtil.equals(msgType, "findReply")) {
//			String taskId = getQueryParameter("taskId", queryForm);
//			String userId = this.getRequest().getCookies().get(1).getValue();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date lastReadTime = null;
//			try {
//				lastReadTime = sdf.parse(getQueryParameter("lastReadTime",
//						queryForm));
//			} catch (ParseException e) {
//			}
//			if (StringUtil.isNotEmpty(taskId)) {
//				// 获取服务
//				allSocialMessageInfo = socialService.findAllSocialMessageInfo(
//						taskId, userId, lastReadTime);
//				if (null != allSocialMessageInfo) {
//					return allSocialMessageInfo;
//				}
//			}
//		} else if (StringUtil.equals(msgType, "getCurrentReadTime")) {
//			SocialMessageInfo msgInfo = new SocialMessageInfo();
//			TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
//			TimeZone.setDefault(tz);
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			msgInfo.setContent(sdf.format(new Date()));
//			return msgInfo;
//		}
//
//		return null;
//	}
//
//	@SuppressWarnings("deprecation")
//	@Post
//	public void addSocialMessageInfo(Representation entity) {
//		Map<String, String> paramsMap = getRequestParams(entity);
//		String userId = URLDecoder.decode(paramsMap.get("userId"));
//		userId = this.getRequest().getCookies().get(1).getValue();
//		String msgId = UUID.randomUUID().toString();
//		String content = URLDecoder.decode(paramsMap.get("content"));
//		int type = Integer.valueOf(URLDecoder.decode(paramsMap.get("type")));
//		String commentedCount = URLDecoder.decode(paramsMap
//				.get("commentedCount"));
//		String commentCount = URLDecoder.decode(paramsMap.get("commentCount"));
//		String transferredCount = URLDecoder.decode(paramsMap
//				.get("transferredCount"));
//		String transferCount = URLDecoder
//				.decode(paramsMap.get("transferCount"));
//		String taskId = URLDecoder.decode(paramsMap.get("taskId"));
//		String processInstanceId = URLDecoder.decode(paramsMap
//				.get("processInstanceId"));
//		int openFlag = Integer.valueOf(URLDecoder.decode(paramsMap
//				.get("openFlag")));
//
//		SocialMessageInfo socialMessageInfo = new SocialMessageInfo();
//		socialMessageInfo.setUserId(userId);
//		socialMessageInfo.setMsgId(msgId);
//		socialMessageInfo.setContent(content);
//		socialMessageInfo.setType(type);
//		socialMessageInfo.setCommentCount(commentCount);
//		socialMessageInfo.setCommentedCount(commentedCount);
//		socialMessageInfo.setTransferredCount(transferredCount);
//		socialMessageInfo.setTransferCount(transferCount);
//		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
//		TimeZone.setDefault(tz);
//		Date createTime = new Date();
//		socialMessageInfo.setTime(createTime);
//		socialMessageInfo.setTaskId(taskId);
//		socialMessageInfo.setProcessInstanceId(processInstanceId);
//		socialMessageInfo.setOpenFlag(openFlag);
//		// 获取服务
//		SocialService socialService = ProcessEngineManagement
//				.getDefaultProcessEngine().getService(SocialService.class);
//		// 构造用户信息
//		socialService.addSocialMessageInfo(socialMessageInfo);
//	}
//}
