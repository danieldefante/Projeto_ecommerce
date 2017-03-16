/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".thumb").hover(function(evt)
{
  
    $(this).css("background", "rgba(0,0,0,0.5)");
    return false;
});

$("thumb").mouseout(function() 
{
  
    $(this).css("background", "red");
    return false;
});