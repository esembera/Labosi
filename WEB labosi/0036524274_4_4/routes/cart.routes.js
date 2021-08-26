const { request } = require('express');
const express = require('express');
const session = require('express-session');
const router = express.Router();
const cart = require('../models/CartModel')
const cartSanitizer = require('./helpers/cart-sanitizer');



// Ulančavanje funkcija međuopreme
router.get('/', cartSanitizer, function (req, res, next) {
    //####################### ZADATAK #######################
    if(req.session.cart === undefined){
        req.session.cart = cart.createCart();
    }
    
    res.render('cart', {
        title: 'Cart',
        user: req.session.user,
        linkActive: 'cart',
        cart: req.session.cart,
        err: undefined
    });
    //#######################################################
});


router.get('/add/:id',async function (req, res, next) {
    //####################### ZADATAK #######################  
    
    await cart.addItemToCart(await req.session.cart, req.params.id, 1);
    res.end();
    

    //#######################################################
});

router.get('/remove/:id',async function (req, res, next) {
    //####################### ZADATAK #######################
    
    await cart.removeItemFromCart(await req.session.cart, req.params.id, 1);
    res.end();
    

    //#######################################################
});



module.exports = router;