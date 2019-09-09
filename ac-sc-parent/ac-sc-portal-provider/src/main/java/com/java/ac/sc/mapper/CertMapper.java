package com.java.ac.sc.mapper;

import com.java.ac.sc.entities.Cert;
import java.util.List;

public interface CertMapper {
    int insert(Cert record);

    List<Cert> selectAll();
    
    List<Cert> getCertListByAcctype(Byte accType);
}