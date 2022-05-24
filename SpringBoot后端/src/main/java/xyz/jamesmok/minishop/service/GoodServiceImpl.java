package xyz.jamesmok.minishop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.jamesmok.minishop.Mapper.GoodMapper;
import xyz.jamesmok.minishop.Mapper.OrderDetailMapper;
import xyz.jamesmok.minishop.entiiy.GoodEntity;
import xyz.jamesmok.minishop.entiiy.OrderDetailEntity;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodServiceImpl implements GoodService{

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public int insertGood(String name, long category_id, String intro, MultipartFile pic, Double price, long count, long saler_id, long browse) throws IOException, SQLException {

        Blob blob = new SerialBlob(pic.getBytes());

        return goodMapper.insertGood(name, category_id, intro, blob, price, count, saler_id, browse);
    }

    @Override
    public Map<String, Object> getGoodAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<GoodEntity> pageInfo = new PageInfo<>(goodMapper.getGoodAll());
        Long total = pageInfo.getTotal();
        List<GoodEntity> good = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", good);
        return map;
    }

    @Override
    public Map<String, Object> getGoodAllCount0(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<GoodEntity> pageInfo = new PageInfo<>(goodMapper.getGoodAllCount0());
        Long total = pageInfo.getTotal();
        List<GoodEntity> good = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", good);
        return map;
    }

    @Override
    public int deleteGoodById(long id) {
        return goodMapper.deleteGoodById(id);
    }

    @Override
    public int batchDeleteGoodById(int[] ids) {
        return goodMapper.batchDeleteGoodById(ids);
    }

    @Override
    public GoodEntity getGoodById(long id) {
        return goodMapper.getGoodById(id);
    }

    @Override
    public Map<String, Object> queryGoodImg(long fileid) {
        return goodMapper.queryGoodImg(fileid);
    }

    @Override
    public int updateGood(GoodEntity good) {
        return goodMapper.updateGood(good);
    }

    @Override
    public int updateGoodImg(String fileid, MultipartFile file) throws IOException, SQLException {
        Blob blob = null;
        blob = new SerialBlob(file.getBytes());
        return goodMapper.updateGoodImg(fileid, blob);
    }

    @Override
    public Map<String, Object> getGoodBySaler(int pageNum, int pageSize, long id) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<GoodEntity> pageInfo = new PageInfo<>(goodMapper.getGoodBySaler(id));
        Long total = pageInfo.getTotal();
        List<GoodEntity> good = pageInfo.getList();
        int PageLength = pageInfo.getPages();
        int PageSize = pageInfo.getSize();
        long PageTotal = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("PageLength", PageLength);
        map.put("PageSize", PageSize);
        map.put("PageTotal", PageTotal);
        map.put("data", good);
        return map;
    }

    @Override
    public List<GoodEntity> getRandomGood(long count) {
        return goodMapper.getRandomGood(count);
    }

    @Override
    public int updateCountOfBroweringGood(long id) {
        return goodMapper.updateCountOfBroweringGood(id);
    }

    @Override
    public Map<String, Object> getGoodSell(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<GoodEntity> goodPageInfo = new PageInfo<>(goodMapper.getGoodAll());
        List<GoodEntity> good = goodPageInfo.getList();
        List<GoodEntity> send = new ArrayList<>();
        for (GoodEntity g : good) {
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<OrderDetailEntity> orderDetailPageInfo = new PageInfo<>(orderDetailMapper.getOrderDetailByGoodId(g.getId()));
            List<OrderDetailEntity> orderDetail = orderDetailPageInfo.getList();
            int count = 0;
            for (OrderDetailEntity od : orderDetail) {
                count += od.getCount();
            }
            GoodEntity temp = new GoodEntity();
            temp.setName(g.getName());
            temp.setCount(count);
            send.add(temp);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", send);
        map.put("PageLength", goodPageInfo.getPages());
        map.put("PageSize", goodPageInfo.getSize());
        map.put("PageTotal", goodPageInfo.getTotal());
        return map;
    }
}
