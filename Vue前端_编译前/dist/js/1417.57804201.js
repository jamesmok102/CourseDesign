"use strict";(self["webpackChunkminishop"]=self["webpackChunkminishop"]||[]).push([[1417],{9541:function(t,e,n){n.d(e,{BB:function(){return s},JU:function(){return a},LF:function(){return u},MU:function(){return i},OV:function(){return l},hd:function(){return d},i9:function(){return c},vD:function(){return o}});var r=n(9496);function l(t){return(0,r.W)({url:`/saler/cart/${t}`,method:"delete"})}function a(t){return(0,r.W)({url:"/saler/cart/batchDelete",method:"delete",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}function u(t,e){return(0,r.W)({url:`/saler/cart_byuser/${t}/${e}`,method:"get"})}function o(t){return(0,r.W)({url:"/userislogin/dealwithcart",method:"post",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}function i(t,e){return(0,r.W)({url:`/userislogin/cart_byuser/${t}/${e}`,method:"get"})}function d(t){return(0,r.W)({url:`/userislogin/cart/${t}`,method:"delete"})}function s(t){return(0,r.W)({url:"/userislogin/cart/batchDelete",method:"delete",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}function c(t,e){return(0,r.W)({url:`/userislogin/cart/selected/${e}`,method:"post",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}},248:function(t,e,n){n.d(e,{BK:function(){return m},Du:function(){return h},Md:function(){return p},RZ:function(){return v},VJ:function(){return g},Zh:function(){return s},_J:function(){return d},g7:function(){return u},jk:function(){return i},rJ:function(){return l},rd:function(){return f},wF:function(){return o},xR:function(){return c},z1:function(){return a}});var r=n(9496);function l(t){return(0,r.W)({url:"/saler/insertGood",method:"post",headers:{"content-type":"multipart/form-data"},data:t})}function a(t){return(0,r.W)({url:`admin/goodsell/${t}`,method:"get"})}function u(t){return(0,r.W)({url:`/admin/good0/${t}`,method:"get"})}function o(t){return(0,r.W)({url:`/admin/good/${t}`,method:"get"})}function i(t){return(0,r.W)({url:`/user/good/${t}`,method:"get"})}function d(t,e){return(0,r.W)({url:`/saler/good_bysaler/${t}/${e}`,method:"get"})}function s(t){return(0,r.W)({url:`/saler/good/${t}`,method:"delete"})}function c(t){return(0,r.W)({url:"/saler/good/batchDelete",method:"delete",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}function f(t){return(0,r.W)({url:`/saler/good_id/${t}`,method:"get"})}function m(t){return(0,r.W)({url:"/saler/updateGood",method:"post",headers:{"content-type":"application/json"},data:JSON.stringify(t)})}function p(t){return(0,r.W)({url:"/saler/updateGoodImg",method:"post",headers:{"content-type":"multipart/form-data"},data:t})}function v(t){return(0,r.W)({url:`/user/randomgood/${t}`,method:"get"})}function g(t){return(0,r.W)({url:`/user/good_id/${t}`,method:"get"})}function h(t){return(0,r.W)({url:`/user/updateBrowse/${t}`,method:"post"})}},1666:function(t,e,n){n.r(e),n.d(e,{default:function(){return V}});var r=n(6252),l=n(3577),a=n(2139),u=n(4719),o=n(386),i=n(7695),d=n(7563),s=n(5911),c=n(7737),f=n(7074);const m=(0,r.Uk)("商品详情"),p=(0,r.Uk)(" 要是合适就买下来吧 "),v=(0,r.Uk)("mdi-account-plus"),g=(0,r.Uk)(" 加入购物车 "),h={style:{width:"30%",display:"inline"}},W={style:{width:"70%",display:"inline"}},y={class:"mt-6"},w={class:"mt-6"},b={class:"mt-6"},_={class:"mt-6"};function k(t,e,n,k,x,$){return(0,r.wg)(),(0,r.iD)(r.HY,null,[(0,r.Wm)(o._,{style:{height:"200px"},color:"#BF6211"},{default:(0,r.w5)((()=>[(0,r.Wm)(i.E,{class:"text-h4 font-weight-black text-white mt-6 ml-4"},{default:(0,r.w5)((()=>[m])),_:1}),(0,r.Wm)(d.Q,{class:"text-h6 font-weight-black text-white mt-4 ml-4"},{default:(0,r.w5)((()=>[p])),_:1}),(0,r.Wm)(s.Z,null,{default:(0,r.w5)((()=>[(0,r.Wm)(u.T,{color:"#402c1a",class:"font-weight-black text-white mt-2 ml-4",plain:"",onClick:k.validate},{default:(0,r.w5)((()=>[(0,r.Wm)(c.t,null,{default:(0,r.w5)((()=>[v])),_:1}),g])),_:1},8,["onClick"])])),_:1})])),_:1}),(0,r.Wm)(o._,{class:"mt-2"},{default:(0,r.w5)((()=>[(0,r._)("div",h,[(0,r.Wm)(a.V,{class:"ma-3",size:"500",rounded:"0"},{default:(0,r.w5)((()=>[(0,r.Wm)(f.f,{src:"http://1.12.253.71:8081/queryGoodImg/"+k.id},null,8,["src"])])),_:1})]),(0,r._)("div",W,[(0,r.Wm)(i.E,{class:"text-h4 font-weight-black mt-4"},{default:(0,r.w5)((()=>[(0,r.Uk)(" 商品名称："+(0,l.zw)(k.list.name),1)])),_:1}),(0,r.Wm)(s.Z,{class:"text-h5 font-weight-black"},{default:(0,r.w5)((()=>[(0,r._)("p",y,"商品种类："+(0,l.zw)(k.list.item),1),(0,r._)("p",w,"商品简介："+(0,l.zw)(k.list.intro),1),(0,r._)("p",b,"商品价格：$"+(0,l.zw)(k.list.price),1),(0,r._)("p",_,"商品库存："+(0,l.zw)(k.list.count),1)])),_:1})])])),_:1})],64)}var x=n(2119),$=n(248),J=n(2262),S=n(9541),C={name:"GoodDetail",setup(){let t=0,e=null;const n=(0,x.yj)(),l=n.query.id;let a=(0,J.iH)([]);const u={good_id:"",user_id:window.localStorage.getItem("UserId"),count:1,selected:1},o=()=>{(0,$.VJ)(l).then((t=>{a.value=t.data,u.good_id=t.data.id,console.log(a.value)})),(0,$.Du)(l).then((t=>{}))};let i=()=>{e=setInterval((()=>{t++}),1e3)};(0,r.bv)((()=>{o(),i()})),(0,r.Jd)((()=>{clearInterval(e),console.log(t)}));const d=()=>{console.log(u),(0,S.vD)(u).then((t=>{console.log(t.data.status)}))};return{id:l,list:a,validate:d}}},I=n(3744);const B=(0,I.Z)(C,[["render",k]]);var V=B},386:function(t,e,n){n.d(e,{_:function(){return B}});var r=n(6252),l=n(2139),a=n(7074),u=n(6474),o=n(3099);const i=(0,r.aZ)({name:"VCardActions",setup(t,e){let{slots:n}=e;return(0,u.AF)({VBtn:{variant:"text"}}),(0,o.L)((()=>{var t;return(0,r.Wm)("div",{class:"v-card-actions"},[null==n||null==(t=n.default)?void 0:t.call(n)])})),{}}});var d=n(5988);const s=(0,d.J)("v-card-avatar"),c=(0,d.J)("v-card-header"),f=(0,d.J)("v-card-header-text"),m=(0,d.J)("v-card-img");var p=n(7563),v=n(5911),g=n(7695),h=n(6454),W=n(8942),y=n(4203),w=n(6947),b=n(3632),_=n(8247),k=n(3664),x=n(2716),$=n(2323),J=n(9748),S=n(7284),C=n(6719),I=n(875);const B=(0,C.a)({name:"VCard",directives:{Ripple:S.H},props:{appendAvatar:String,appendIcon:String,disabled:Boolean,flat:Boolean,hover:Boolean,image:String,link:Boolean,prependAvatar:String,prependIcon:String,ripple:Boolean,subtitle:String,text:String,title:String,...(0,$.x$)(),...(0,h.m)(),...(0,W.f)(),...(0,y.x)(),...(0,w.c)(),...(0,b.F)(),...(0,_.I)(),...(0,k.GN)(),...(0,x.Q)(),...(0,J.bk)({variant:"contained"})},setup(t,e){let{attrs:n,slots:u}=e;const{themeClasses:o}=(0,$.ER)(t),{borderClasses:d}=(0,h.P)(t),{colorClasses:x,colorStyles:S,variantClasses:C}=(0,J.c1)(t),{densityClasses:B}=(0,W.t)(t),{dimensionStyles:V}=(0,y.$)(t),{elevationClasses:z}=(0,w.Y)(t),{positionClasses:A,positionStyles:D}=(0,b.K)(t),{roundedClasses:U}=(0,_.b)(t),j=(0,k.nB)(t,n);return()=>{var e,n,h;const W=j.isLink.value?"a":t.tag,y=!(!u.title&&!t.title),w=!(!u.subtitle&&!t.subtitle),b=y||w,_=!!(u.append||t.appendAvatar||t.appendIcon),k=!!(u.prepend||t.prependAvatar||t.prependIcon),$=!(!u.image&&!t.image),Z=b||k||_,N=!(!u.text&&!t.text),O=!t.disabled&&(j.isClickable.value||t.link);return(0,r.wy)((0,r.Wm)(W,{class:["v-card",{"v-card--disabled":t.disabled,"v-card--flat":t.flat,"v-card--hover":t.hover&&!(t.disabled||t.flat),"v-card--link":O},o.value,d.value,x.value,B.value,z.value,A.value,U.value,C.value],style:[S.value,V.value,D.value],href:j.href.value,onClick:O&&j.navigate},{default:()=>[(0,J.Ux)(O,"v-card"),$&&(0,r.Wm)(I.z,{defaults:{VImg:{cover:!0,src:t.image}}},{default:()=>[(0,r.Wm)(m,null,{default:()=>[u.image?null==(e=u.image)?void 0:e.call(u):(0,r.Wm)(a.f,{alt:""},null)]})]}),null==(n=u.media)?void 0:n.call(u),Z&&(0,r.Wm)(c,null,{default:()=>[k&&(0,r.Wm)(I.z,{defaults:{VAvatar:{density:t.density,icon:t.prependIcon,image:t.prependAvatar}}},{default:()=>[(0,r.Wm)(s,null,{default:()=>[u.prepend?u.prepend():(0,r.Wm)(l.V,null,null)]})]}),b&&(0,r.Wm)(f,null,{default:()=>[y&&(0,r.Wm)(g.E,null,{default:()=>[u.title?u.title():t.title]}),w&&(0,r.Wm)(p.Q,null,{default:()=>[u.subtitle?u.subtitle():t.subtitle]})]}),_&&(0,r.Wm)(I.z,{defaults:{VAvatar:{density:t.density,icon:t.appendIcon,image:t.appendAvatar}}},{default:()=>[(0,r.Wm)(s,null,{default:()=>[u.append?u.append():(0,r.Wm)(l.V,null,null)]})]})]}),N&&(0,r.Wm)(v.Z,null,{default:()=>[u.text?u.text():t.text]}),null==(h=u.default)?void 0:h.call(u),u.actions&&(0,r.Wm)(i,null,{default:u.actions})]}),[[(0,r.Q2)("ripple"),O]])}}})},7563:function(t,e,n){n.d(e,{Q:function(){return l}});var r=n(5988);const l=(0,r.J)("v-card-subtitle")},5911:function(t,e,n){n.d(e,{Z:function(){return l}});var r=n(5988);const l=(0,r.J)("v-card-text")},7695:function(t,e,n){n.d(e,{E:function(){return l}});var r=n(5988);const l=(0,r.J)("v-card-title")}}]);
//# sourceMappingURL=1417.57804201.js.map