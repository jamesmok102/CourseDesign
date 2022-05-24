import {request} from "./request";

export function getSalers(page) {
    return request({
        url:`/admin/salers/${page}`,
        method:'get',
    })
}

export function deleteSalersById(id) {
    return request({
        url:`/admin/salers/${id}`,
        method:'delete',
    })
}

export function betchDeleteSalersById(data) {
    return request({
        url:'/admin/salers/batchDelete',
        method:'delete',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function insertSaler(data) {
    return request({
        url:'/admin/salers/insertSaler',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}

export function getSalerById(id) {
    return request({
        url:`/admin/saler_id/${id}`,
        method:'get',
    })
}

export function getSalerById_forSaler(id) {
    return request({
        url:`/saler/saler_id/${id}`,
        method:'get',
    })
}

export function updateSaler(data) {
    return request({
        url:'/admin/updateSaler',
        method:'post',
        headers: {'content-type': 'application/json'},
        data: JSON.stringify(data)
    })
}

export function uploadSalerImg(data) {
    return request({
        url:'/admin/updateSalerImg',
        method:'post',
        headers: {'content-type': 'multipart/form-data'},
        data
    })
}



