const IMP = window.IMP;
IMP.init("imp12648182")

const button = document.querySelector("button");
const onClickPay = async () =>{
    IMP.request_pay({
        pg: "kakaopay",
        pay_method: "card",
        amount: "60000",
        name: "데드풀과 울버린",
        merchant_uid: "ORD20231030-000001"

    });
};


button.addEventListener("click", onClickPay);