package com.blc.customerInterface.graphql.pem.mutation;

import com.blc.customerInterface.graphql.pem.domain.Pem;
import com.blc.customerInterface.graphql.pem.mutation.input.PemCreateInput;
import com.blc.customerInterface.graphql.pem.mutation.input.PemUpdateInput;
import com.blc.customerInterface.graphql.pem.mutation.mapper.PemMapper;
import com.blc.customerInterface.graphql.pem.service.PemService;
import com.blc.customerInterface.graphql.permission.domain.PermissionName;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Component
@Validated
public class PemMutation implements GraphQLMutationResolver {
    private final PemService pemService;
    private final PemMapper pemMapper;

    @Autowired
    public PemMutation(PemService pemService, PemMapper pemMapper) {
        this.pemService = pemService;
        this.pemMapper = pemMapper;
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.PEM_CREATE +"')")
    public Pem createPem(PemCreateInput input){
        return pemService.save(pemMapper.toEntity(input));
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.PEM_UPDATE +"')")
    public Pem updatePem(UUID id, PemUpdateInput input){
        return pemService.findById(id).map(pem ->pemService.update(pemMapper.updateEntity(pem,input))).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.PEM_DELETE +"')")
    public UUID deletePem(UUID id){
        return pemService.findById(id).map(pemService::delete).orElseThrow(RuntimeException::new);
    }
    @PreAuthorize("hasAuthority('"+ PermissionName.PEM_UNDELETE +"')")
    public Pem undeletePem(UUID id){
        return pemService.findById(id).map(pemService::undelete).orElseThrow(RuntimeException::new);
    }
}
