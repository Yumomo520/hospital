package com.goodmap.hospital.service.people_track;

import com.goodmap.hospital.pojo.PeopleTrack;
import com.goodmap.hospital.service.base.BaseService;

/**
 * @author 刘智强
 * @date 2021/4/26
 * @Description
 */
public interface PeopleTrackService extends BaseService<PeopleTrack> {

    void addL(PeopleTrack peopleTrack);
}
