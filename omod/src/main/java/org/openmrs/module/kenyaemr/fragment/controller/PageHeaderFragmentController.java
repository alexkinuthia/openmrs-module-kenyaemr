/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyaemr.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaemr.KenyaEmr;
import org.openmrs.module.kenyaemr.util.BuildProperties;
import org.openmrs.module.kenyaemr.api.KenyaEmrService;
import org.openmrs.module.kenyaemr.util.KenyaEmrUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.text.ParseException;
import java.util.Date;

/**
 *
 */
public class PageHeaderFragmentController {

	public void controller(FragmentModel model, @SpringBean KenyaEmr emr) throws ParseException {
		// Get module version
		String moduleVersion = emr.getModuleVersion();

		// Fetch build date for snapshot versions
		Date moduleBuildDate = null;
		if (moduleVersion.endsWith("SNAPSHOT")) {
			BuildProperties buildProperties = emr.getModuleBuildProperties();

			if (buildProperties != null) {
				moduleBuildDate = buildProperties.getBuildDate();
			}
		}

		model.addAttribute("moduleVersion", moduleVersion);
		model.addAttribute("moduleBuildDate", moduleBuildDate);

		model.addAttribute("systemLocation", Context.getService(KenyaEmrService.class).getDefaultLocation());
		model.addAttribute("systemLocationCode", Context.getService(KenyaEmrService.class).getDefaultLocationMflCode());
	}
}