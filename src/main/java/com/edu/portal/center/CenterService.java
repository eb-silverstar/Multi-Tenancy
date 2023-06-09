package com.edu.portal.center;

import com.edu.portal.common.ApiException;
import com.edu.portal.common.Constants;
import com.edu.portal.common.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterMapper centerMapper;

    /**
     * 센터 목록 조회
     *
     * @param map
     * @return Map<String, String>
     */
    public Map<String, Object> getCenters(Map<String, String> map) {
        map.putIfAbsent("page", "1");
        map.putIfAbsent("limit", "10");
        map.putIfAbsent("filter", "");
        map.putIfAbsent("order", "uno");
        map.putIfAbsent("sort", "desc");
        map.put("offset", String.valueOf(Integer.parseInt(map.get("limit")) * (Integer.parseInt(map.get("page")) - 1)));

        // filter parsing
        for(Map<String, String> fMap : Utils.parseFilter(map.get("filter"))) {
            map.put(fMap.get("col"), fMap.get("con"));
        }

        // order parsing
        map.put("order_by", Utils.parseOrder(map.get("order"), map.get("sort")));

        Map<String, Object> result = new HashMap<>();
        result.put("count", centerMapper.cntCenters(map));
        result.put("rows", centerMapper.getCenters(map));

        return result;
    }

    /**
     * 센터 정보 조회
     *
     * @param uno
     * @return CenterDTO
     */
    public CenterDTO getCenter(int uno) {
        return centerMapper.getCenter(uno);
    }

    /**
     * 센터 정보 조회
     *
     * @param cntrNm
     * @return CenterDTO
     */
    public CenterDTO getCenter(String cntrNm) {
        return centerMapper.getCenterByNm(cntrNm);
    }

    /**
     * 센터 등록
     *
     * @param center
     * @return CenterDTO
     */
    public CenterDTO createCenter(CenterDTO center) {
        if(getCenter(center.getCntrNm()) != null) {
            throw new ApiException(Constants.ALREADY_DATA);
        }

        centerMapper.insertCenter(center);

        return getCenter(center.getUno());
    }

    /**
     * 센터 정보 수정
     *
     * @param uno
     * @param center
     * @return int
     */
    public int modifyCenter(int uno, CenterDTO center) {
        center.setUno(uno);
        int result = centerMapper.updateCenter(center);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

    /**
     * 센터 삭제
     *
     * @param uno
     * @return int
     */
    public int deleteCenter(int uno) {
        int result = centerMapper.deleteCenter(uno);

        if(result == 0) {
            throw new ApiException(Constants.NO_DATA);
        }

        return result;
    }

}
