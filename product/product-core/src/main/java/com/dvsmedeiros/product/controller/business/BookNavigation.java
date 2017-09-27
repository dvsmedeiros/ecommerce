package com.dvsmedeiros.product.controller.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.dvsmedeiros.bce.core.controller.ITask;
import com.dvsmedeiros.bce.core.controller.business.impl.CodeGenerator;
import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.product.controller.business.impl.BookFilter;
import com.dvsmedeiros.product.domain.Book;

@Configuration
public class BookNavigation {
		
	@Autowired
	public BookNavigation(ThreadPoolTaskScheduler scheduler, List<ITask> tasks) {
		for (ITask task : tasks) {
			scheduler.scheduleAtFixedRate(task, task.getFixedRate());
		}
	}
	
	@Autowired
	private CodeGenerator codeGenerator;
	@Autowired
	private BookFilter filterBook;
	
	@Bean(name = "SAVE_BOOK")
	public Navigation<Book> saveBook() {

		return new NavigationBuilder<Book>()
				.next(codeGenerator)
				.build();
	}
	
	@Bean(name = "FILTER_BOOK")
	public Navigation<Filter<Book>> filterBook() {

		return new NavigationBuilder<Filter<Book>>()
				.next(filterBook)
				.build();
	}

}
