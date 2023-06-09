package com.edu.portal.center;

import com.edu.portal.common.ApiResponseEntity;
import com.edu.portal.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    /**
     * 센터 목록 조회
     *
     * @param map
     * @return
     */
    @GetMapping("/list/{page}/{limit}/{filter}/{order}/{sort}")
    public ResponseEntity<ApiResponseEntity> getCenters(@PathVariable Map<String, String> map) {
        Map<String, Object> result = centerService.getCenters(map);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 센터 정보 조회
     *
     * @param uno
     * @return
     */
    @GetMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> getCenter(@PathVariable("uno") int uno) {
        CenterDTO result = centerService.getCenter(uno);

        if(result != null) {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(false, Constants.NO_DATA, result), HttpStatus.OK);
        }
    }

    /**
     * 센터 등록
     *
     * @param center
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponseEntity> createCenter(@RequestBody CenterDTO center) {
        CenterDTO result = centerService.createCenter(center);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 센터 정보 수정
     *
     * @param uno
     * @param center
     * @return
     */
    @PutMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> modifyCenter(@PathVariable("uno") int uno, @RequestBody CenterDTO center) {
        int result = centerService.modifyCenter(uno, center);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

    /**
     * 센터 삭제
     *
     * @param uno
     * @return
     */
    @DeleteMapping("/{uno}")
    public ResponseEntity<ApiResponseEntity> deleteCenter(@PathVariable("uno") int uno) {
        int result = centerService.deleteCenter(uno);

        return new ResponseEntity<ApiResponseEntity>(new ApiResponseEntity(true, Constants.SUCCESS, result), HttpStatus.OK);
    }

}