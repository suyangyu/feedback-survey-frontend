$(document).ready(function() {
     $('input[type="checkbox"]').click(function () {
          if ($(this).attr('text') == "None of these")
          {
            $('input[type=checkbox]').each(function () {
                if ($(this).is(":checked") && $(this).attr('text') != "None of these"){
                   $(this).attr('checked', false)
                }
            });
          }
          else {
            $('input[text="None of these"]').attr('checked', false)
          }
          ga('send', 'event', this.id, 'click');
     });
     $('input[type="radio"]').click(function(){
         ga('send', 'event', this.id, 'click');
     });
});