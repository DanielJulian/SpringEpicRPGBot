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
public class ScheduledCommandService {

    private final String RPG_COMMAND = "rpg ";

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    AppConfiguration configuration;

    @Scheduled(fixedDelayString = "${app.work-delay}")
    private void work() throws InterruptedException {
        randomDelay();
        String workToPerform = getRandomFromList(configuration.getWorkCommands());
        System.out.println("Executing work schedule " + workToPerform);
        applicationEventPublisher.publishEvent(buildCommandEvent(workToPerform));
    }

    @Scheduled(fixedDelayString = "${app.hunt-delay}")
    private void hunt() throws InterruptedException {
        randomDelay();
        String huntToPerform = getRandomFromList(configuration.getHuntCommands());
        System.out.println("Executing hunt schedule " + huntToPerform);
        applicationEventPublisher.publishEvent(buildCommandEvent(huntToPerform));
    }

     /*

   def weekly(self, initial_cooldown=0):
      time.sleep(initial_cooldown)
      while True:
         self.command_queue.put('rpg weekly')
         time.sleep(604800) # 1 Week

   def daily(self, initial_cooldown=0):
      time.sleep(initial_cooldown)
      while True:
         self.command_queue.put('rpg daily')
         time.sleep(86500) # 24 Hours

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

   def adventure(self, initial_cooldown=0):
      time.sleep(initial_cooldown)
      while True:
         self.command_queue.put('rpg heal')
         self.command_queue.put('rpg adventure')
         time.sleep(3601 + randint(0, 10)) # 1 hour + random secs
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
