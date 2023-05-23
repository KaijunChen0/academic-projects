package com.kj.mywiki.service;

import com.kj.mywiki.domain.Test;
import com.kj.mywiki.mapper.TestMapper;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TestService {
  @Resource
  private TestMapper testMapper;
  public List<Test> list(){
    return testMapper.list();
  }
}
