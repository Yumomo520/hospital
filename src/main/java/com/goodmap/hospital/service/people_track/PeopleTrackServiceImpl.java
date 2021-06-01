package com.goodmap.hospital.service.people_track;

import com.goodmap.hospital.mapper.PeopleTrackMapper;
import com.goodmap.hospital.pojo.PeopleTrack;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘智强
 * @date 2021/4/26
 * @Description
 */
@Service
public class PeopleTrackServiceImpl extends BaseServiceImpl<PeopleTrack> implements PeopleTrackService {

    @Autowired
    private PeopleTrackMapper peopleTrackMapper;

    @Override
    public void addL(PeopleTrack peopleTrack) {
        peopleTrackMapper.addL(peopleTrack);
    }
}
