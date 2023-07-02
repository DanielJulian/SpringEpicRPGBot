package com.danny.epicrpgbot.command;

import com.danny.epicrpgbot.config.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
@EnableScheduling
public class ScheduledCommandPublisher {

    private final String RPG_COMMAND = "rpg ";

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    AppConfiguration configuration;

    @Scheduled(fixedDelayString = "${app.work-delay}")
    private void work() throws InterruptedException {
        randomDelay();
        String workCommand = getRandomFromList(configuration.getWorkCommands());
        applicationEventPublisher.publishEvent(buildCommandEvent(workCommand));
    }

    @Scheduled(fixedDelayString = "${app.hunt-delay}")
    private void hunt() throws InterruptedException {
        randomDelay();
        String huntCommand = getRandomFromList(configuration.getHuntCommands());
        applicationEventPublisher.publishEvent(buildCommandEvent(huntCommand));
    }

    @Scheduled(fixedDelayString = "${app.weekly-delay}")
    private void weekly() throws InterruptedException {
        randomDelay();
        String weeklyCommand = getRandomFromList(configuration.getWeeklyCommands());
        applicationEventPublisher.publishEvent(buildCommandEvent(weeklyCommand));
    }

    @Scheduled(fixedDelayString = "${app.daily-delay:86400000}")
    private void daily() throws InterruptedException {
        randomDelay();
        String dailyCommand = "daily";
        applicationEventPublisher.publishEvent(buildCommandEvent(dailyCommand));
    }

    @Scheduled(fixedDelayString = "${app.adventure-delay:3600000}")
    private void adventure() throws InterruptedException {
        randomDelay();
        applicationEventPublisher.publishEvent(buildCommandEvent("heal"));
        applicationEventPublisher.publishEvent(buildCommandEvent("adventure"));
    }

     /*


   def lootbox(self, initial_cooldown=0):
      time.sleep(initial_cooldown)
      while True:
         self.command_queue.put('rpg withdraw 500k')
         self.command_queue.put('rpg buy edgy lootbox')
         self.command_queue.put('rpg deposit all')
         time.sleep(10900) # 3 Hours

   def farm(self, initial_cooldown=0):
      time.sleep(initial_cooldown)

      def get_seeds_in_inventory():
         seed_list = []
         for item_name, amount in self.inventory_cache.get_consumables().items():
            if 'seed' in item_name and amount > 0 and item_name != 'seed':
               seed_list.append(item_name)
         return seed_list

      while True:
         self.command_queue.put("rpg i") # To refresh inventory cache
         time.sleep(10)
         command = 'rpg farm'
         seed_list = get_seeds_in_inventory()
         if (seed_list):
            command += " " + random.choice(seed_list)
         self.command_queue.put(command)
         time.sleep(610 + randint(0, 10)) # 10 minutes 10 secs + random


     * */

    private CommandEvent buildCommandEvent(String command) {
        return new CommandEvent(this, RPG_COMMAND + command);
    }

    private void randomDelay() throws InterruptedException { // add random delay between 1 and 10 seconds
        Thread.sleep(RandomGenerator.getDefault().nextLong(1, 10) * 1000);
    }

    private <T> T getRandomFromList(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

}
