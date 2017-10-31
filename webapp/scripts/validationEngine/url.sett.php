<?php

/**
 * @copyright (C) 2009 by KHCOM
 * @version 1.0
 * @update 3/25/2009
 */
 
/**
 * Site urls map.
 * @package config
 */

global $_URL;   //URL direct mapping
global $_RURL;  //URL reverse mapping patterns

$_URL['public.user']           = $_CONF['url'].'{$name}';

$_URL['public.rss']            = $_CONF['url'].'public/rss/';

$_RURL['|/index.php/public/user/([^/]+)$|']         = '/index.php/public/user/name_$1';
$_RURL['|/index.php/public/user/([^/]+)/([^/]+)$|'] = '/index.php/public/user/$2/name_$1';

?>