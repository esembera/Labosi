var express = require('express');
var router = express.Router();
var db = require('../db');

router.get('/', async function(req, res, next) {
    var data = await db.query(`select * from categories`);
    var trueData = data.rows;
    var categories = await db.query(`select * from inventory`);
    var trueCategories = categories.rows;
    res.render('order', {
        title: 'Order',
        linkActive: 'order',
        data: trueData,
        categories: trueCategories
    });
});

module.exports = router;