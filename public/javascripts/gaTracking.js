$(".ga-track-event").click(function(){
  ga('send', 'event', $(this).data('ga-event-category'), $(this).data('ga-event-action'), $(this).data('ga-event-label'));
});
