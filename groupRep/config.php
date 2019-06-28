<?php
/**
 * all the variables defined whitin this file are always accesable by this site
 */

/** database config */

/** MySQL database name */
define('DB_NAME', 'HotelCal');

/** MySQL database username */
define('DB_USER', 'hotelCal');

/** MySQL database password */
define('DB_PASSWORD', '12QWas!@34ERdf#$');

/** MySQL hostname */
define('DB_HOST', '83.87.28.180:3306');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8mb4');
// end of database config

// site config
/** page groups whitin the site */
define('PAGES', ['admin', 'cleaners', 'frontdesk', 'guest', 'pricing']);

/** wheter debug output is given or not */
define('DEBUG', 1);

// site page groups locking definition set
//wich area's are protected area's
define('LOCKEDAREAS', ['admin', 'cleaners', 'frontdesk', 'guest', 'pricing']);

//which acces levels are allowed acces to that area
define('ACCESLEVEL', ['admin'=>[1], 'cleaners'=>[1, 2], 'frontdesk'=>[1, 3], 'guest'=>[4], 'pricing'=>[1, 3]]);

//to what must the ridirect happen is acces is wanted
define('LOCKEDAREAREDIRECTPAGE', ['admin'=>'admin/login', 'cleaners'=>'admin/login', 'frontdesk'=>'admin/login', 'guest'=>'admin/login', 'pricing'=>'admin/login']);

//exempted pages and excecutors from the rule of acces denial
define('LOCKEDAREAALLOWEDPAGES', ['admin'=>['admin/login', 'admin/register', 'admin/logout']]);

//the dashboards asigned to each acces level TODO: transform this into standard admin/dashboard and make that page dynamic based on user acces level
define('DASHBOARDS', [0 => '404', 1 => 'admin/dashboard']);
// end site config
