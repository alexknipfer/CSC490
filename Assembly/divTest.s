	.file	"divTest.c"
	.text
	.globl	foo
	.type	foo, @function
foo:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	%edi, -4(%rbp)
	nop
	popq	%rbp
	ret
	.size	foo, .-foo
	.globl	main
	.type	main, @function
main:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	$4, %edi
	call	foo
	movl	$0, %eax
	popq	%rbp
	ret
.LFE1:
	.size	main, .-main
	.ident	"GCC: (Solus) 6.2.0"
	.section	.note.GNU-stack,"",@progbits
