/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 * @author ych
 */
package org.foxbpm.engine.impl.cmd;

import java.util.zip.ZipInputStream;

import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.util.ExceptionUtil;
import org.foxbpm.engine.repository.DeploymentBuilder;

public class UpdateDeploymentByZipCmd implements Command<Void>{
	
	protected ZipInputStream zipInputStream;
	protected DeploymentBuilder deploymentBuilder;
	protected String deploymentId;
	public UpdateDeploymentByZipCmd(DeploymentBuilder deploymentBuilder,String deploymentId,ZipInputStream zipInputStream){
		this.zipInputStream = zipInputStream;
		this.deploymentId = deploymentId;
		this.deploymentBuilder = deploymentBuilder;
	}
	public Void execute(CommandContext commandContext) {
		if(zipInputStream == null){
			throw ExceptionUtil.getException("10601003");
		}
		if(deploymentId == null || "".equals(deploymentId)){
			throw ExceptionUtil.getException("10601006");
		}
		deploymentBuilder.updateDeploymentId(deploymentId);
		deploymentBuilder.addZipInputStream(zipInputStream);
		deploymentBuilder.deploy();
		return null;
	}

}