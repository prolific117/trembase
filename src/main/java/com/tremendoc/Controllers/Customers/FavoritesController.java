/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.Customers;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.response.FavoritesResponse;

/**
 *
 * @author olatunji.oduro
 */
public interface FavoritesController {
    
    public ServiceResponse addFavorite(SessionDetail sessionDetail, Long doctorId);
    public ServiceResponse removeFavorite(SessionDetail sessionDetail, Long doctorId);
    public FavoritesResponse getFavorites(SessionDetail sessionDetail);
}
