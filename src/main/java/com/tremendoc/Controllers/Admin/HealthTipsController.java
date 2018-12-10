/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Admin;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.response.HealthTips;

/**
 *
 * @author olatunji.oduro
 */
public interface HealthTipsController {
    public ServiceResponse likeHealthTip(Long id);
    public ServiceResponse removeHealthTip(Long id);
    public HealthTips getHealthTips(int page);
    public ServiceResponse createorEditHealthTip(Long id, String body, String summary, String title, String imagePath);
}
