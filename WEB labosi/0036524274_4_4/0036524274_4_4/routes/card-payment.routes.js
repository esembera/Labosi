const express = require('express');
const session = require('express-session');
const router = express.Router();

// Ulančavanje funkcija međuopreme
router.get('/', function (req, res, next) {
    //####################### ZADATAK #######################
    if(req.session.user===undefined){
        req.session.err="Please login to view the requested page.";
        res.redirect('/login');
    }
    res.render('card-payment', {
        title: 'card-payment',
        user: req.session.user,
        linkActive: 'login',
        err: undefined,
        cardholdername: req.session.cardholdername,
        cardnumber: req.session.cardnumber,
        cardccv: req.session.cardccv,
        cardexpiration: req.session.cardexpiration
    });
    //#######################################################
});

router.post('/', function (req, res, next) {
    //####################### ZADATAK #######################


    res.render('card-payment', {
        title: 'card-payment',
        user: req.session.user,
        linkActive: 'login',
        err: undefined,
        cardholdername: req.session.cardholdername,
        cardnumber: req.session.cardnumber,
        cardccv: req.session.cardccv,
        cardexpiration: req.session.cardexpiration
    });
    //#######################################################
});

router.post('/save', function (req, res, next) {
    //####################### ZADATAK #######################
    req.session.cardholdername = req.body["cardholder-name"];
    req.session.cardnumber = req.body["card-number"];
    req.session.cardccv = req.body["card-ccv"];
    req.session.cardexpiration = req.body["card-expiration"];

    console.log(req.session);
    res.redirect('/cart');
    //#######################################################
});


router.post('/reset', function (req, res, next) {
    //####################### ZADATAK #######################
    req.session.cardholdername = undefined;
    req.session.cardnumber = undefined;
    req.session.cardccv = undefined;
    req.session.cardexpiration = undefined;


    res.render('card-payment', {
        title: 'card-payment',
        user: req.session.user,
        linkActive: 'login',
        err: undefined,
        cardholdername: req.session.cardholdername,
        cardnumber: req.session.cardnumber,
        cardccv: req.session.cardccv,
        cardexpiration: req.session.cardexpiration
    });
    //#######################################################
});


module.exports = router;